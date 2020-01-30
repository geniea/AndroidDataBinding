#Android Architecture \#1 Data binding


![data binding](https://user-images.githubusercontent.com/60108801/73315298-0faeaf00-4273-11ea-8b36-96f540883737.png)


##1.findViewById() 제거

###Step 1
build.gradle (Module: app) file의 android block 내에 데이터 바인딩 추가.

```
android {
    ...
    dataBinding {
        enabled = true
    }
}
```

###Step 2

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

###Step 3

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

findViewById\<View\>(R.id.id_view) 를 binding.idView 로 사용함.
