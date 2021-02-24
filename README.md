# SearchImage
Kakao openApi 활용 Image 검색 어플리케이션
<br>

[![Build Status](https://travis-ci.com/sysout-achieve/SearchImage.svg?branch=master)](https://travis-ci.com/sysout-achieve/SearchImage)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
<br>

### 구성 : <br>
- Language : Kotlin <br>
- DI : Hilt <br>
- AAC(MVVM, DataBinding, LiveData, Navigation, Paging) <br>
- Opensource: RxKotlin, RxBinding, Retrofit, Glide <br>
- Test : Unit Test(JUnit4), Ui Test(Espresso)
- TravisCI

<br>
<hr>
<br>

### MVVM
 : View와 ViewModel의 책임과 역할을 분리합니다.
* 화면의 변경이 ViewModel의 코드에 직접적인 영향을 주지 않습니다. (요구하는 데이터 자체가 변경되는 경우 제외) 
* 각 역할에 맞는 기능 구현으로 책임이 분배되고 Testable한 코드가 됩니다.
#### DataBinding
 : ViewModel과 뷰컴포넌트 객체를 연결지어주는 클래스를 generate합니다.<br>
 바인딩한 데이터를 xml상에서 참조할 수 있어 화면(Activity, Fragment)의 코드가 간결해집니다.  
#### LiveData
 : Observer 패턴을 이용하여 ViewModel의 데이터를 View에서 관찰대상으로 동작시킬 수 있습니다.<br>
 이를 이용하여 View와 ViewModel의 의존도를 더 떨어뜨릴 수 있습니다.

<br>

### Repository Pattern
 : ViewModel에서 데이터 호출 시 repository에 요청합니다.
* ViewModel은 데이터를 가져오는 곳이 DB인지 API 호출인지 알 필요가 없습니다. (loose coupling)
* 데이터를 호출하는 곳이 내부에서 외부로 변경되거나 호출하는 Api가 변경되어도 ViewModel의 소스에 영향이 없습니다.

<br>

### Navigation (Single Activity Architecture)
 : 하나의 Activity로 application 구성합니다.
* navigation.xml로 대략적인 앱 구조 확인이 가능합니다.
* 화면 전환과 관련된 코드가 navigation.xml로 이동하여 책임을 분리합니다.

<br>

### TEST
 : Test code를 수행하면서 만들어진 product의 최소한의 품질을 보장할 수 있습니다. 
* 요구사항에 중점을 둔 Test code로 요구사항 만족을 보장합니다.
* 테스트 코드 작성을 위해 코드간 의존성을 줄이게 되고 객체지향적 코드를 만들 수 있게 됩니다. (의존도가 높을수록 테스트 어려움)
 
<br>
