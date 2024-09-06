<table>
  <tr>
    <td><img width="500" alt="img_memozi_banner1" src="https://github.com/user-attachments/assets/a8e1c88b-c500-4767-b221-d6299475e1eb"></td>
    <td><img width="500" alt="img_memozi_banner2" src="https://github.com/user-attachments/assets/d7a83064-36b7-4ab8-8306-4a8c927b1462"></td>
  </tr>
</table>


## 🔢 목차
[프로젝트 설명](#프로젝트-설명)</br>
[주요 기능](#주요-기능)</br>
[기술 및 아키텍쳐 선정](#기술-및-아키텍쳐-선정)</br>
[컨벤션 규칙 및 브랜치 전략](#컨벤션-규칙-및-브랜치-전략)</br>
[팀원별 역할 분담](#팀원별-역할-분담)</br>
[폴더링](#폴더링)</br>

## 🅿️ 프로젝트 설명 
** 메모지는 일상 속에 메모와 사진과 함께 쓰는 일기를 적을 수 있는 메모 앱 서비스 입니다. **

## ⚙️ 기술 및 아키텍쳐 선정
+ `IDE - Android Studio koala`</br>
+ `Architecture - MVI, Clean Architecture`</br>
+ `DI - Hilt`</br>
+ `Network - Retrofit`</br>
+ `Asynchronous - Coroutine, Flow`</br>
+ `Jetpack - DataStore, Navigation, `</br>
+ `CI - Github Actions`</br>
+ `Image - coil`</br>
+ `lint - ktlint`</br>
+ `third party - amazon aws, kakao login `</br></br>

## 📍 주요 기능 
1. 메모 기능:  글과 체크박스를 통해 메모를 작성 수정하는 기능
2. 카테고리 기능: 카테고리 별로 메모를 저장하고 묶을 수 있는 기능
3. 일기 기능: 사진과 함께 텍스트로 하루하루 일기를 작성하는 기능
4. 알림 기능: 특정 시간별로 일기를 쓰도록 알림을 보내는 기능
5. 검색 기능: 특정 키워드로 검색하는 기능
   
## 👤 팀원별 역할 분담
|<img src="https://avatars.githubusercontent.com/u/75840431?v=4" width="170" />|<img src="https://avatars.githubusercontent.com/u/137873124?v=4" width="170" />|<img src="https://avatars.githubusercontent.com/u/137160756?v=4" width="170"/>|<img src="https://avatars.githubusercontent.com/u/86788873?v=4" width="170" />|
|:---------:|:---------:|:---------:|:---------:|
|[👑김명석](https://github.com/cacaocoffee)|[주효은](https://github.com/hyoeunjoo)|[손민재](https://github.com/SYAAINN)|[김민우](https://github.com/DoReMinWoo)|
| `메모화면, 메모 카테고리 추가, 기초세팅` | `메모 검색, 메모 상세화면` | `일기 화면(리스트형/캘린더형)` | `로그인 UI, 온보딩, 설정` | </br>


## ❗ 컨벤션 규칙 및 브랜치 전략

**깃 컨벤션:**  [Git Convention](https://www.notion.so/Branch-Strategy-2b1a261ec26b4f88a915701024a019be?pvs=4) </br>
**코드 컨벤션:**  [Code Convention](https://www.notion.so/Git-Convention-d9d669bcc4b448ffa3240c31b0f352d8?pvs=4) </br>
**브랜치 전략:**  [Branch Strategy](https://www.notion.so/Code-Convention-fb683fa7b3104b23907b07fd7499d521?pvs=4) </br></br>
 
## 🗂️ 폴더링

```bash
├── Memozi
├── 📁:app
├── 📁:build-logic
│   ├── 📁 convention
├── 📁:core
│   ├── 🗂️ buildconfig
├── 📁:data
│   ├── 🗂️ auth
│   ├── 🗂️ oauth
├── 📁:local
│   ├── 🗂️ auth
├── 📁:remote
│   ├── 🗂️ auth
├── 📁:domain
│   ├── 🗂️ auth
│   ├── 🗂️ oauth
├── 📁:feature
│   ├── 🗂️ navigator
│   ├── 🗂️ login
│   ├── 🗂️ memo
│   ├── 🗂️ diary
├── 📁:gradle
│   ├──  libs.versions.toml
```
