# project_SayAgain 6th study
node js 및 html, firebase web hosting을 사용한 관리자 웹 페이지 제작(1)
##주제 firebase web hosting이란? (firebase web hosting관련 학습위주 및 기본 웹 제작 구성)

**기존의 앱 또한 firebase를 이용해 authentification을 구현하였고, 서버를 firebase에서 사용하고 있는 상태
**웹은 사용자X, 관리자 전용

###firebase web hosting을 사용하는 이유는 지금 웹페이지를 만드는 목적이 SayAgain의 웹 용을 만들려는 것이 아닌
SayAgain에서 사용되는 데이터베이스를 관리하고 사용자들의 이용 현황 및 사용자가 지정한 어려운 단어, 모르는 단어들을
관리자가 알기 쉽게 하기 위한 것이므로, firebase web hosting을 통해 간편하게 웹과 앱을 엑세스 하려고 하기 때문이다.
또한 firebase의 서비스들이 좋기도 하다.

###또한 firebase는 웹 사이트 서비스를 열때 정해놓은 제한선안에서 무료로 제공해준다. Spark요금제 이용중


지금 단계에서 firebase는 웹의 서버를 해주는 역할과 데이터베이스 관리를 할려고 이용. 그러나 진행 중 더 추가될 수 있음
웹은 Node.js모듈을 사용

웹을 만들기전에 어떤 것이 필요한지 미리 ui를 제작함.

![1](https://user-images.githubusercontent.com/31847834/57533507-e8c4eb80-7378-11e9-9976-9d77da6edc2f.PNG)

<웹 메인메뉴>

![2](https://user-images.githubusercontent.com/31847834/57533578-1873f380-7379-11e9-9c61-db99baf8ed7a.PNG)

<영어 원문 관리 - 주된 데이터베이스>
	
![3](https://user-images.githubusercontent.com/31847834/57533580-19a52080-7379-11e9-9eae-375fe9e255e8.PNG)

<사용자 학습 관리 - 각 사용자의 학습 성취도를 확인>

### firebase연동 과정

![4](https://user-images.githubusercontent.com/31847834/57533607-24f84c00-7379-11e9-8e57-b57fc0467f7c.PNG)

<firebase연동 과정>

![5](https://user-images.githubusercontent.com/31847834/57533610-26297900-7379-11e9-8a0f-7710cae819be.PNG)

<연동 후 생성된 웹의 URL 화면>

![6](https://user-images.githubusercontent.com/31847834/57533651-3ccfd000-7379-11e9-98b9-78ff8c09e22d.PNG)

<firebase Hosting 홈 화면 - 앱과 함께 웹에 제공되었음>

![7](https://user-images.githubusercontent.com/31847834/57533653-3e999380-7379-11e9-8dbb-bc9ced3140d6.PNG)

<현재까지 한 부분>

앞으로 구상해놓은 ui를 기반으로 제작을 할 예정, 디자인은 따로 생각하지 않고 만든 후 
완성시킨 후 디자인에 대해 고민해볼게요,,디자인은 자신이 없기때문,,

이번 스터디는 firebase web hosting을 처음 써보기도했고, web을 뭘로 만들지 고민하다가 유튜브를 추천받아서 제작을 하게되었음.
