# kaldi 설치 - https://github.com/kaldi-asr/kaldi 공식 kaldi 오픈소스 이용
앞서 말한 것 처럼, kaldi는 UNIX기반,, Windows에서는 기존의 설치방식이 통하지 않는다
/windosws/INSTALL.md파일을 참고

## OpenFST설치가 필요.
![1](https://user-images.githubusercontent.com/31847834/58751915-ad818c80-84e0-11e9-9b3a-fbf130bc4c60.PNG)

### OpenFST란, weighted finite-state transducers(FST)를 구성하고 결합, 최적화, 검색하는 라이브러리이다. 

현재 kaldi를 설치하고 적용하는데 필요한 project.

또한 OpenFST를 컴파일하기 위해선, CMake를 다운로드 해야함. cmake를 통해  openfst를 visual studio 2017버전으로 열 수 있는 환경을 만든다.

![2](https://user-images.githubusercontent.com/31847834/58753247-373c5480-84f7-11e9-981a-96bcb12fab13.PNG)
-Visual Studio에서 openfst 솔루션을 열음. openfst.sln

kaldi의 tool 킷을 이용해 OpenBLAS 열어 환경 유지,  NVIDIA CUDA 설치,  

![image](https://user-images.githubusercontent.com/31847834/58753415-ce0a1080-84f9-11e9-9391-cd91b3962a68.png)

![image](https://user-images.githubusercontent.com/31847834/58753412-c185b800-84f9-11e9-99af-76515cae543a.png)

### 최종적으로, kaldi 프로젝트를 Visual Studio로 빌드할 수 있는 환경을 만들었음.

http://jcsilva.github.io/2017/03/18/compile-kaldi-android/ -> 안드로이드 환경에서 사용할 수 있는 방법이 적혀있음

### Android

- Kaldi supports cross compiling for Android using Android NDK, clang++ and
  OpenBLAS.
- See [this blog post](http://jcsilva.github.io/2017/03/18/compile-kaldi-android/)
  for details.

