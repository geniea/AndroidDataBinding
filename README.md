# Android Architecture \[ Data binding ]


![data binding](https://user-images.githubusercontent.com/60108801/73315298-0faeaf00-4273-11ea-8b36-96f540883737.png)


## 1.findViewById() 사용 안하기
 findViewById() 함수를 사용하면 runtime에 view를 검색하게되는데, layout에 view 가 많을 경우 바람직하지 않음.


### Step 1
build.gradle (Module: app) file의 android block 내에 데이터 바인딩 추가.

```
android {
    ...
    dataBinding {
        enabled = true
    }
}
```

### Step 2

layout 파일 최외각에  \<layout\>\</layout\> 태그를 추가하고, namespaces를 태그 내로 이동함.

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
   xmlns:app="http://schemas.android.com/apk/res-auto">
   <androidx.constraintlayout.widget.ConstraintLayout ... >
   ...
   </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

빌드하면 아래 그림과 같이 databinding 패키지 및 관련 class 생성됨.

![enter image description here](https://user-images.githubusercontent.com/60108801/73413477-288c9280-434f-11ea-8a16-c70b68f4aa31.png)

### Step 3

MainActivity file에 binding object 생성 및 초기화

```java
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
```

findViewById\<View\>(R.id.id_view) 를 binding.idView 로 대체하여 사용함.

## 2.데이터 Display

data binding은  view에 data class 내용을 직접 표시할 수 있음.

### Step 4

data class  추가

```java
package com.jineesoft.androiddatabinding.data
data class Person(val name: String = "", var age: Int)
```

### Step 5

layout 파일의 <layout> 태그 다음에 <data> 및 <variable> 태그 삽입

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable
            name = "person"
            type = "com.jineesoft.androiddatabinding.data.Person" />
    </data>
    <androidx.constraintlayout.widget.ConstraintLayout
    .....
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```


### Step 6

reference data variable
```xml
<TextView
    android:id="@+id/textView"
    ...
    android:text="@={person.name}"
    ... />
```
### Step 7
데이터 생성
```java
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val p = Person( "Jang", 40)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.person = p
        binding.buttonName.setOnClickListener {
            changeName()
        }
    }
    private fun changeName( ){
        p.name = "name Changed"
        binding.invalidateAll()  //need to update view
    }
}
```
