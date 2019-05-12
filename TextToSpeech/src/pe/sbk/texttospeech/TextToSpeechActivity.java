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
 * �Էµ� ���ڸ� �Ҹ��� �о��ִ� TextToSpeech ����
 * @author mailss
 */
public class TextToSpeechActivity extends Activity implements OnClickListener, OnInitListener {
	private Button mSpeak, mSpeak2;					//���� ��� ��ư, ����� �Է±��� ��¹�ư, ���� �ؽ�Ʈ ��� ��ư
	private ToggleButton mOption;						//�ɼ� ���� ��ư
	private RelativeLayout mCustom, mSample;			//�ִϸ��̼� �ֱ� ���� ���̰� ���� ���̾ƿ� 
	private EditText mInput;								//����� �Է� ����Ʈ �ؽ�Ʈ
	private Spinner mLanguage, mSampleText;			//��� ���� ���ǳ�, ���� �ؽ�Ʈ ���ǳ�
	private TextToSpeech mTTS;							//TextToSpeech ��ü
	private ArrayList<Locale> mLanguages;				//����� ���(����)
	private boolean isInit, isSupport;						//TextToSpeech�� �ʱ�ȭ �Ǿ�����, �����ϴ� ������� �Ǵ��ϴ� �÷���
	private SeekBar mPitch, mRate;						//pitch, rate ���� seekbar
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		initData();			//�ۿ��� ����ϴ� �����͸� �ʱ�ȭ �Ѵ�
		initView();			//�並 �ʱ�ȭ �Ѵ�
	}
	
	/**
	 * �ۿ��� ����ϴ� �����͸� �ʱ�ȭ �Ѵ�.
	 */
	private void initData() {
		mTTS = new TextToSpeech(this, this);		//tts ��ü ����
		mLanguages = new ArrayList<Locale>();	//����� ��� ����Ʈ ����
		mLanguages.add(Locale.US);					//����
		mLanguages.add(Locale.FRANCE);			//��������
		mLanguages.add(Locale.ITALY);				//��Ż���ƾ�
		mLanguages.add(Locale.KOREA);				//�ѱ���
	}
	
	/**
	 * �並 �ʱ�ȭ �Ѵ�.
	 */
	private void initView() {
		mSpeak = (Button)findViewById(R.id.btn_speak);		//����� �Է� ����ϴ� ��ư
		mSpeak.setOnClickListener(this);							//��ư Ŭ�� �̺�Ʈ ������ ���
		
		mInput = (EditText)findViewById(R.id.text);				//����� �Է� �޴� ����Ʈ �ؽ�Ʈ
		
		mSpeak2 = (Button)findViewById(R.id.btn_speak2);	//���� �ؽ�Ʈ ����ϴ� ��ư
		mSpeak2.setOnClickListener(this);							//��ư Ŭ�� �̺�Ʈ ������ ���

		mSampleText = (Spinner)findViewById(R.id.sample_text);	//���� �ؽ�Ʈ �����ϴ� ���ǳ�
		
		mCustom = (RelativeLayout)findViewById(R.id.custom);	//����� �Է��ϴ� ȭ��
		mSample = (RelativeLayout)findViewById(R.id.sample);	//���� �ؽ�Ʈ ����ϴ� ȭ��
		
		mOption = (ToggleButton)findViewById(R.id.btn_option);	//�ɼ� ��ư.
		mOption.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) showSample();		//�������� ���� ���̾ƿ��� �����ְ�
				else showCustom();					//������ ����� �Է�ȭ���� �����ش�
			}
		});
		
		mLanguage = (Spinner)findViewById(R.id.lang);	//��� ���� ���ǳ�
		mLanguage.setOnItemSelectedListener(new OnItemSelectedListener() {		//�� ����������
			@Override
			public void onItemSelected(AdapterView<?> spinner, View v, int position, long id) {
				if(position == 0) return;			//ù��° ������. �� �����϶�� ���ڸ� �ƹ��͵� ����.
				int available = mTTS.isLanguageAvailable(mLanguages.get( position-1 ));	//�ش� �� �����ϴ� ������� �˻�
				
				if(available < 0) {					//�������� ������ �޽��� ���
					Toast.makeText(getApplicationContext(), R.string.msg_not_support_lang, Toast.LENGTH_SHORT).show();
					isSupport = false;
				}
				else isSupport = true;				//�����ϴ� ����̸� �÷��� ����
			}

			@Override public void onNothingSelected(AdapterView<?> spinner) {}
		});
		
		mPitch = (SeekBar)findViewById(R.id.seek_pitch);		//pitch ����
		mRate = (SeekBar)findViewById(R.id.seek_rate);		//rate ����
	}
	
	/**
	 * ���� �ؽ�Ʈ�� �����ϴ� ȭ���� �����ش�
	 */
	private void showSample(){
		Animation inLeft = AnimationUtils.loadAnimation(this, R.anim.in_left);		//���� ȭ���� �����ʿ��� �������� ������ �ִϸ��̼�
		
		Animation outLeft = AnimationUtils.loadAnimation(this, R.anim.out_left);	//����� �Է�ȭ���� �������� ������ �ִϸ��̼�
		outLeft.setAnimationListener(new AnimationListener() {
			@Override public void onAnimationStart(Animation animation) {}
			@Override public void onAnimationRepeat(Animation animation) {}
			@Override public void onAnimationEnd(Animation animation) { mCustom.setVisibility(View.GONE); }	//�ִϸ��̼� ������ ����� �Է�ȭ�� ����
		});
		
		mSample.setVisibility(View.VISIBLE);		//���� ȭ�� ���̱�
		mSample.startAnimation(inLeft);			//���� ȭ�� �ִϸ��̼� ����
		mCustom.startAnimation(outLeft);			//����� �Է�ȭ�� �ִϸ��̼� ����
	}
	
	/**
	 * ����� �Է�ȭ������ �����ش�
	 */
	private void showCustom(){
		Animation inRight = AnimationUtils.loadAnimation(this, R.anim.in_right);			//����� �Է�ȭ���� ���ʿ��� ���������� ������ �ִϸ��̼�
		
		Animation outRight = AnimationUtils.loadAnimation(this, R.anim.out_right);		//���� ȭ���� ���������� ������ �ִϸ��̼�
		outRight.setAnimationListener(new AnimationListener() {
			@Override public void onAnimationStart(Animation animation) {}
			@Override public void onAnimationRepeat(Animation animation) {}
			@Override public void onAnimationEnd(Animation animation) { mSample.setVisibility(View.GONE); }		//�ִϸ��̼� ������ ����ȭ�� ����
		});
		
		mSample.startAnimation(outRight);		//����ȭ�� �ִϸ��̼� ����
		mCustom.setVisibility(View.VISIBLE);		//����� �Է�ȭ�� ���̱�
		mCustom.startAnimation(inRight);			//����� �Է�ȭ�� �ִϸ��̼� ����
	}

	/**
	 * ���� ��� ��ư�� Ŭ���ϸ� ó���ϴ� �޼ҵ�
	 * @param v - Ŭ���� �� ��ü
	 */
	@Override
	public void onClick(View v) {
		if(!isInit)				//�ʱ�ȭ �ȵǾ����� �޽��� ���
			Toast.makeText(this, R.string.msg_fail_init, Toast.LENGTH_SHORT).show();
		else if(mLanguage.getSelectedItemPosition() == 0)	//��� ���� �������� �޽��� ���
			Toast.makeText(this, R.string.msg_select_lang, Toast.LENGTH_SHORT).show();
		else if(!isSupport)	//�������� �ʴ� ����̸� �޽��� ���
			Toast.makeText(getApplicationContext(), R.string.msg_not_support_lang, Toast.LENGTH_SHORT).show();
		else {
			String text = v == mSpeak ? mInput.getText().toString() : mSampleText.getSelectedItem().toString();	//�ؽ�Ʈ ������
			if(TextUtils.isEmpty(text))			//�� �ؽ�Ʈ�� �޽��� ���
				Toast.makeText(this, R.string.msg_success_init, Toast.LENGTH_SHORT).show();
			else {
				Locale lang = mLanguages.get(mLanguage.getSelectedItemPosition()-1);	//������ ���
				mTTS.setLanguage(lang);									//��� ����.
				mTTS.setPitch(mPitch.getProgress()/10.0f);				//pitch ����.
				mTTS.setSpeechRate(mRate.getProgress()/10.0f);		//rate ����.
				mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);	//�ش� ���� �ؽ�Ʈ ���� ���
			}
		}
	}

	/**
	 * TextToSpeech �ʱ�ȭ ������ ���� �޼ҵ�
	 * @param status - ���� �ڵ�
	 */
	@Override
	public void onInit(int status) {
		isInit = status == TextToSpeech.SUCCESS;								//�������� ����
		int msg = isInit ? R.string.msg_success_init : R.string.msg_fail_init;	//�޽��� ����
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();				//�޽��� ���
	}
	
	/**
	 * �� ����� �Ҹ���
	 */
	@Override
	public void finish() {
		if(mTTS != null) {			//TextToSpeech ��ü�� �����Ǿ�����
			mTTS.stop();				//���� ��� ����
			mTTS.shutdown();		//TextToSpeech ����
		}
		super.finish();
	}
}