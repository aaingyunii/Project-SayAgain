package pe.sbk.texttospeech;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * 입력된 글자를 소리로 읽어주는 TextToSpeech 예제
 * @author mailss
 */
public class TextToSpeechActivity extends Activity implements OnClickListener, OnInitListener {
	private Button mSpeak, mSpeak2;					//음성 출력 버튼, 사용자 입력글자 출력버튼, 샘플 텍스트 출력 버튼
	private ToggleButton mOption;						//옵션 선택 버튼
	private RelativeLayout mCustom, mSample;			//애니메이션 주기 위해 보이고 감출 레이아웃 
	private EditText mInput;								//사용자 입력 에디트 텍스트
	private Spinner mLanguage, mSampleText;			//언어 선택 스피너, 샘플 텍스트 스피너
	private TextToSpeech mTTS;							//TextToSpeech 객체
	private ArrayList<Locale> mLanguages;				//사용할 언어(국가)
	private boolean isInit, isSupport;						//TextToSpeech가 초기화 되었는지, 지원하는 언어인지 판단하는 플래그
	private SeekBar mPitch, mRate;						//pitch, rate 조절 seekbar
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		initData();			//앱에서 사용하는 데이터를 초기화 한다
		initView();			//뷰를 초기화 한다
	}
	
	/**
	 * 앱에서 사용하는 데이터를 초기화 한다.
	 */
	private void initData() {
		mTTS = new TextToSpeech(this, this);		//tts 객체 생성
		mLanguages = new ArrayList<Locale>();	//사용할 언어 리스트 생성
		mLanguages.add(Locale.US);					//영어
		mLanguages.add(Locale.FRANCE);			//프랑스어
		mLanguages.add(Locale.ITALY);				//이탈리아어
		mLanguages.add(Locale.KOREA);				//한국어
	}
	
	/**
	 * 뷰를 초기화 한다.
	 */
	private void initView() {
		mSpeak = (Button)findViewById(R.id.btn_speak);		//사용자 입력 출력하는 버튼
		mSpeak.setOnClickListener(this);							//버튼 클릭 이벤트 리스너 등록
		
		mInput = (EditText)findViewById(R.id.text);				//사용자 입력 받는 에디트 텍스트
		
		mSpeak2 = (Button)findViewById(R.id.btn_speak2);	//샘플 텍스트 출력하는 버튼
		mSpeak2.setOnClickListener(this);							//버튼 클릭 이벤트 리스너 등록

		mSampleText = (Spinner)findViewById(R.id.sample_text);	//샘플 텍스트 선택하는 스피너
		
		mCustom = (RelativeLayout)findViewById(R.id.custom);	//사용자 입력하는 화면
		mSample = (RelativeLayout)findViewById(R.id.sample);	//샘플 텍스트 출력하는 화면
		
		mOption = (ToggleButton)findViewById(R.id.btn_option);	//옵션 버튼.
		mOption.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) showSample();		//눌려지면 샘플 레이아웃을 보여주고
				else showCustom();					//꺼지면 사용자 입력화면을 보여준다
			}
		});
		
		mLanguage = (Spinner)findViewById(R.id.lang);	//언어 선택 스피너
		mLanguage.setOnItemSelectedListener(new OnItemSelectedListener() {		//언어를 선택했으면
			@Override
			public void onItemSelected(AdapterView<?> spinner, View v, int position, long id) {
				if(position == 0) return;			//첫번째 아이템. 언어를 선택하라는 글자면 아무것도 안함.
				int available = mTTS.isLanguageAvailable(mLanguages.get( position-1 ));	//해당 언어가 지원하는 언어인지 검사
				
				if(available < 0) {					//지원하지 않으면 메시지 출력
					Toast.makeText(getApplicationContext(), R.string.msg_not_support_lang, Toast.LENGTH_SHORT).show();
					isSupport = false;
				}
				else isSupport = true;				//지원하는 언어이면 플래그 변경
			}

			@Override public void onNothingSelected(AdapterView<?> spinner) {}
		});
		
		mPitch = (SeekBar)findViewById(R.id.seek_pitch);		//pitch 조절
		mRate = (SeekBar)findViewById(R.id.seek_rate);		//rate 조절
	}
	
	/**
	 * 샘플 텍스트를 선택하는 화면을 보여준다
	 */
	private void showSample(){
		Animation inLeft = AnimationUtils.loadAnimation(this, R.anim.in_left);		//샘플 화면이 오른쪽에서 왼쪽으로 들어오는 애니메이션
		
		Animation outLeft = AnimationUtils.loadAnimation(this, R.anim.out_left);	//사용자 입력화면이 왼쪽으로 나가는 애니메이션
		outLeft.setAnimationListener(new AnimationListener() {
			@Override public void onAnimationStart(Animation animation) {}
			@Override public void onAnimationRepeat(Animation animation) {}
			@Override public void onAnimationEnd(Animation animation) { mCustom.setVisibility(View.GONE); }	//애니메이션 끝나면 사용자 입력화면 감춤
		});
		
		mSample.setVisibility(View.VISIBLE);		//샘플 화면 보이기
		mSample.startAnimation(inLeft);			//샘플 화면 애니메이션 시작
		mCustom.startAnimation(outLeft);			//사용자 입력화면 애니메이션 시작
	}
	
	/**
	 * 사용자 입력화면으로 보여준다
	 */
	private void showCustom(){
		Animation inRight = AnimationUtils.loadAnimation(this, R.anim.in_right);			//사용자 입력화면이 왼쪽에서 오른쪽으로 들어오는 애니메이션
		
		Animation outRight = AnimationUtils.loadAnimation(this, R.anim.out_right);		//샘플 화면이 오른쪽으로 나가는 애니메이션
		outRight.setAnimationListener(new AnimationListener() {
			@Override public void onAnimationStart(Animation animation) {}
			@Override public void onAnimationRepeat(Animation animation) {}
			@Override public void onAnimationEnd(Animation animation) { mSample.setVisibility(View.GONE); }		//애니메이션 끝나면 샘플화면 감춤
		});
		
		mSample.startAnimation(outRight);		//샘플화면 애니메이션 시작
		mCustom.setVisibility(View.VISIBLE);		//사용자 입력화면 보이기
		mCustom.startAnimation(inRight);			//사용자 입력화면 애니메이션 시작
	}

	/**
	 * 음성 출력 버튼을 클릭하면 처리하는 메소드
	 * @param v - 클릭된 뷰 객체
	 */
	@Override
	public void onClick(View v) {
		if(!isInit)				//초기화 안되었으면 메시지 출력
			Toast.makeText(this, R.string.msg_fail_init, Toast.LENGTH_SHORT).show();
		else if(mLanguage.getSelectedItemPosition() == 0)	//언어 선택 안했으면 메시지 출력
			Toast.makeText(this, R.string.msg_select_lang, Toast.LENGTH_SHORT).show();
		else if(!isSupport)	//지원하지 않는 언어이면 메시지 출력
			Toast.makeText(getApplicationContext(), R.string.msg_not_support_lang, Toast.LENGTH_SHORT).show();
		else {
			String text = v == mSpeak ? mInput.getText().toString() : mSampleText.getSelectedItem().toString();	//텍스트 가져옴
			if(TextUtils.isEmpty(text))			//빈 텍스트면 메시지 출력
				Toast.makeText(this, R.string.msg_success_init, Toast.LENGTH_SHORT).show();
			else {
				Locale lang = mLanguages.get(mLanguage.getSelectedItemPosition()-1);	//선택한 언어
				mTTS.setLanguage(lang);									//언어 설정.
				mTTS.setPitch(mPitch.getProgress()/10.0f);				//pitch 설정.
				mTTS.setSpeechRate(mRate.getProgress()/10.0f);		//rate 설정.
				mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);	//해당 언어로 텍스트 음성 출력
			}
		}
	}

	/**
	 * TextToSpeech 초기화 리스너 구현 메소드
	 * @param status - 상태 코드
	 */
	@Override
	public void onInit(int status) {
		isInit = status == TextToSpeech.SUCCESS;								//성공여부 저장
		int msg = isInit ? R.string.msg_success_init : R.string.msg_fail_init;	//메시지 설정
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();				//메시지 출력
	}
	
	/**
	 * 앱 종료시 불린다
	 */
	@Override
	public void finish() {
		if(mTTS != null) {			//TextToSpeech 객체가 생성되었으면
			mTTS.stop();				//음성 출력 중지
			mTTS.shutdown();		//TextToSpeech 종료
		}
		super.finish();
	}
}