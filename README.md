# project_SayAgain STT - 변경 예정
## 앱 개발전 구상한 UI (일부분) - 대화형 컨텐츠 학습
![1](https://user-images.githubusercontent.com/31847834/58319058-123f4600-7e54-11e9-9dde-323473eb9543.PNG)
![2](https://user-images.githubusercontent.com/31847834/58319083-1e2b0800-7e54-11e9-8280-39d35ca08858.PNG)



## 앱의 개발상황

![KakaoTalk_20190524_183603406](https://user-images.githubusercontent.com/31847834/58318647-28003b80-7e53-11e9-8cbe-fad1874c06e0.jpg)
1. 첫화면

![KakaoTalk_20190524_183603311](https://user-images.githubusercontent.com/31847834/58318704-4d8d4500-7e53-11e9-92d4-d2cf68100d16.jpg)

2. 메인메뉴

![KakaoTalk_20190524_183603209](https://user-images.githubusercontent.com/31847834/58318753-64339c00-7e53-11e9-9c0d-9e7391bbbda7.jpg)

3. 대화형 컨텐츠학습 - 현재 기능의 테스트를 위해서 여러 API를 코딩함으로써 테스트를 함

![KakaoTalk_20190524_183628301](https://user-images.githubusercontent.com/31847834/58318827-89280f00-7e53-11e9-9993-e3950de36b1a.jpg)

4. TTS 테스트 - 테스트를 위해 따로 텍스트 입력창을 만든 후 입력받아 글자를 터치 시 보이스로 입력한 단어를 출력,
밑의 버튼들은 출력 보이스의 음 높낮이, 읽는 속도를 다르게 설정하여 듣기좋은 정도를 찾는 활동

![KakaoTalk_20190524_183602910](https://user-images.githubusercontent.com/31847834/58319298-9a255000-7e54-11e9-8374-9e0adf6bf7fe.jpg)![KakaoTalk_20190524_183603030](https://user-images.githubusercontent.com/31847834/58319306-a01b3100-7e54-11e9-9ef4-d4b8c7a51d50.jpg)![KakaoTalk_20190524_183602814](https://user-images.githubusercontent.com/31847834/58319317-a4dfe500-7e54-11e9-8cb3-bdcddef520ca.jpg)

5. STT 테스트 - 마이크 테스트를 위해 만듬, 영문장을 읽었을 시, 음성메세지를 텍스트문자로 전환하여 화면창에 입력받는 기능, 테스트 화면을 보시다시피 'Nice to meet you'를 말했을 때, 영문장으로 입력되는 부분도 있지만 한글로 '나이스 투 미츄'로 입력될 수 있음. 
이외에 긴 문장을 읽었을 때, 말한 내용과 전혀다른 내용이 입력되는 경우가 다수 발생하였음. 처음 개발당시의 테스트에서는 가벼운 음성메세지는 잘 받아드렸으나 이후의 테스트에서 실망스러운 모습이 많았음.

### 원인
- 마이크테스트 버튼을 클릭시, (음성메세지를 입력받는 순간) 핸드폰내에서 음성이 시작되는 시점과 끝나는 시점의 정확한 지점을 찾는 것이 어려워 음성메세지를 잘 인식하지 못한것. 예시로 빅스비는 '하이 빅스비'라는 관용적인 단어를 인식한 후, 음성의 시작점을 명시함. 아이폰의 siri도 같은 맥락(siri야).
이러한 부분은 개발이 어렵기 때문에 다른 알고리즘을 참고할 것. 자연어 처리 및 비교적 큰 데이터를 처리해야하는데 기본적으로 개발환경이 열악함. 
### Kaldi STT
- 검색 결과로 현재 오픈되어있는 API 중 제일 좋은 STT를 보여줌.
- 참고로 TTS의 경우 현재의 기능들이 거의 완벽하게 적용되며, 읽는 속도 조절과 음 높낮이도 완벽하게 가능함으로 그대로 유지.

