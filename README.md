# Android-ReturnPlus
"陌上花开"是一个面向青少年的抑郁症辅助应用的 Android 项目。


# 陌上开花App———开发记录

## 启动界面的设计

创建名称为`Splash`的Activity的Java文件，并在对应的.xml文件中设置背景Login图片：

```xml
<?xml version="1.0" encoding="utf-8"?>
<!--设置线性布局-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/iv1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/Login"
        android:background="#ADAAA9"/>
</LinearLayout>
```

图片地址：

<img src="https://p.ipic.vip/duz8xw.png" alt="Login" style="zoom: 33%;" />



<font color = red>非常重要</font>：修改`AndroidManifest.xml`文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/Theme.App_plus"
        tools:targetApi="31">
        <activity
            android:name=".LoginActivity"
            android:exported="false" />
        <activity
            android:name=".Splash"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name=".MainActivity"
            android:exported="true"></activity>
    </application>

</manifest>
```







## 设计登录界面

修改`activity_login.xml`文件

- 图片：ImageView
  - src：表示使用的图片的位置，用@drawable/xxxx引用，或者java文件中R.drawable

- 可编辑的文本框：EditText
  - android:hint=“请输入用户名”		提示文字
  - android:drawableLeft="@drawable/icon_password_32"   在某一个位置显示图标
  - android:singleLine=“true” 默认是多行输入，设置成true，表示只允许单行
  - android:inputType：表示输入的类型

- 登录按钮：Button
- 复选框：CheckBox
- 只读文本：TextView



```java
package com.example.areturn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvForgetPassword;

    private TextView etUsername;
    private TextView etPassword;
    private CheckBox autoLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //设置全屏模式
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        //设置布局文件
        setContentView(R.layout.activity_login);

        //找到按钮
        btnLogin = findViewById(R.id.btn_Login);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        autoLogin = findViewById(R.id.autologin);

        //给按钮添加点击事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("LoginActivity", "按钮被点击了，准备登录......");



                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username == null || "".equals(username)){
                    etUsername.setError("用户名不能为空");
                    return;
                }
                if (password == null || "".equals(password)){
                    etPassword.setError("密码不能为空");
                    return;
                }

                //TODO 将用户名密码发送到后段验证
                //admin 123456
                if (!"admin".equals(username) || !"123456".equals(password)){
                    etUsername.setError("用户名或密码不正确");
                    return;
                }

                //添加自动登录
                saveAutoLogin();

                //从当前Activity跳转到MainActivity页面
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                //关闭当前Activity
                finish();

                //使用意图启动Activity
                startActivity(intent);
                }
        });


        //设置点击忘记密码时跳转到对应网站，例如http://www.baidu.coms
        tvForgetPassword.setText(Html.fromHtml("<a href=\"http://www.baidu.com\">忘记密码？</a>"));
        tvForgetPassword.setMovementMethod(LinkMovementMethod.getInstance());
    }


    private void saveAutoLogin(){
        if (autoLogin.isChecked()){
            //打印输出日志
            Log.d("LoginActivity","自动登录");

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            SharedPreferences sp = getSharedPreferences("AUTO_LOGIN", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();

            //存在内存中
            edit.putString("username", username);
            edit.putString("password", password);

            //将数据写到文件中
            edit.commit();

        }else {
            //打印输出日志
            Log.d("LoginActivity","非自动登录");
        }
    }
}
```



布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".LoginActivity"
    android:padding="15dp">



<!--  头像设置为水平居中  -->
    <ImageView
        android:id="@+id/icon1"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/icon"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="30dp"/>


    <!--        设置用户-->
    <EditText
        android:id="@+id/username"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_below="@id/icon1"
        android:hint="用户名"
        android:textSize="20sp"
        android:textColor="#FFAD33"
        android:maxLines="1"
        android:drawableLeft="@drawable/icon_user_32"
        android:layout_marginTop="80dp"
        android:background="@drawable/bg_frame1"/>

    <!--        设置密码-->
    <EditText
        android:id="@+id/password"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_below="@id/username"
        android:hint="密码"
        android:inputType="textPassword"
        android:textSize="20sp"
        android:textColor="#FFAD33"
        android:maxLines="1"
        android:drawableLeft="@drawable/icon_password_32"
        android:layout_marginTop="40dp"
        android:background="@drawable/bg_frame1"/>



    <Button
        android:id="@+id/btn_Login"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:background="@drawable/bg_btn1"
        android:text="登录"
        android:textSize="@dimen/btnSize"
        android:layout_below="@id/password"
        android:layout_marginTop="40dp"/>


    <CheckBox
        android:id="@+id/autologin"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="自动登录"
        android:textSize="15sp"
        android:layout_below="@id/btn_Login"
        android:layout_alignLeft="@id/autologin"
        android:layout_marginTop="20dp"/>

    <TextView
        android:id="@+id/tvForgetPassword"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="忘记密码?"
        android:textSize="16sp"
        android:layout_alignRight="@id/btn_Login"
        android:layout_alignBaseline="@id/autologin"
        android:textColor="@color/blue"/>

</RelativeLayout>
```











## 实现自动登录

- 登录的时候将用户名和密码保存到SharePreferences

- 添加启动画面（作为第一画面）

  - ```xml
    <!--隐式意图-->
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
    
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
    ```

  启动的时候判断SharePreferences，有则打开MainActivity，否则打开LoginActivity。



getPreferences方法创建文件的模式：

- MODE_APPEND：如果该文件已经存在，将数据以追加的方式写入，而不清除现有文件
- MODE_PRIVATR：默认模式，创建的文件只能由应用程序调用
- MODE_WORLD_READABLE：允许所有其他应用程序有读取和创建文件的权限
- MODE_WORLD_WRITABLE：允许所有其他应用程序具有写入、访问和创建的文件权限







登录LoginActivity

```java
private void saveAutoLogin(){
    if (autoLogin.isChecked()){
        //打印输出日志
        Log.d("LoginActivity","自动登录");

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        SharedPreferences sp = getSharedPreferences("ATUo_LOGIN", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        //存在内存中
        edit.putString("username", username);
        edit.putString("password", password);

        //将数据写到文件中
        edit.commit();
    }else {
        //打印输出日志
        Log.d("LoginActivity","非自动登录");
    }
}
```



调试查看存储的文件

​	使用SDK中提供的资源管理器





任务2:用户名/密码验证

- 获取EditText中的内容

  getText();

- 设置错误信息

  setError();

- 获取焦点

  requestFocus();



## 添加启动画面（启动页）

- 创建SplashActivity
- 启动Activity的时候判断是否有自动登录的信息

<img src="/Users/zechaowei/Library/Application%20Support/typora-user-images/image-20230429233559055.png" alt="image-20230429233559055" style="zoom:33%;" />

设置SplashActivity全屏：

```java
//设置全屏模式
getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
```

<img src="/Users/zechaowei/Library/Application%20Support/typora-user-images/image-20230429233639068.png" alt="image-20230429233639068" style="zoom:33%;" />



去掉标题行：

```java
//去掉标题行
supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
```



<img src="/Users/zechaowei/Library/Application%20Support/typora-user-images/image-20230429233420387.png" alt="image-20230429233420387" style="zoom: 33%;" />

最终演示效果：

<img src="https://p.ipic.vip/aeav2n.png" alt="image-20230429233916795" style="zoom:33%;" />

Splash Activity代码

```java
package com.example.areturn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在设置布局之前，隐藏下ActionBar

        //设置全屏模式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_splash);
        handler.sendEmptyMessageDelayed(0,3000);


//        //判断在SharedPreferences中存在的登录信息，则跳转到MainActivity，否则跳转到LoginActivity
//        SharedPreferences sp = getSharedPreferences("AUTO_LOGIN", Context.MODE_PRIVATE);
//
//        String username = sp.getString("username","");
//        String password = sp.getString("password","");
//
//
//        //应该判断Token，目前没有网络请求，暂时使用判断用户名、密码代替
//        Intent intent = null;
//        if ((username != null && username.length() > 0 )&&
//                (password != null && password.length() > 0)){
//            //MainActivity
//            intent = new Intent(this, MainActivity.class);
//        }else {
//            //LoginActivity
//            intent = new Intent(this, LoginActivity.class);
//        }
    }
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    public void getHome(){
        //判断在SharedPreferences中存在的登录信息，则跳转到MainActivity，否则跳转到LoginActivity
        SharedPreferences sp = getSharedPreferences("AUTO_LOGIN", Context.MODE_PRIVATE);

        String username = sp.getString("username","");
        String password = sp.getString("password","");


        //应该判断Token，目前没有网络请求，暂时使用判断用户名、密码代替
        Intent intent = null;
        if ((username != null && username.length() > 0 )&&
                (password != null && password.length() > 0)){
            //MainActivity

            //TODO 注意：此处的LoginActivity，始终都会跳转到Login界面，后面在进行调整
            intent = new Intent(SplashActivity.this, MainActivity.class);
        }else {
            //LoginActivity
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
```

对应xml文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SplashActivity"
    android:background="@drawable/icon_login">

</androidx.constraintlayout.widget.ConstraintLayout>
```











## 弹窗Dialog

- 对话框的种类
- 提醒对话框
- 单选对话框
- 多选对话框
- 自定义对话框等



AertDialog的创建需要它的一个内嵌类Builder

```java
d.setIcon();
d.setMessage();
d.setItems();
d.setSingleChoiceItems();
d.setMultiChoiceItems();
d.setPositiveButton();		//确定按钮
d.setNegativeButton();		//取消按钮
d.setNeutralButton();			//忽略按钮
d.show();
d.dismiss();
```

```java
//普通对话框
Dialog d = new AlertDialog.Builder(this)
  .setIcon(R.drawable.ipod8)
  .setTitle("警告")
  .setMessage("确定退出吗？")
  .setPositiveButton("确定",new DialogInterface.OnClickListener(){
      @Override
      public void OnClick(DialogInterface dialog, int which){
        finish();
      }
  })
  .setNegativeButton("取消",null)
  .setneutralButton("忽略",null)
  .show();
```

java代码

```java
package com.example.areturn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在设置布局之前，隐藏下ActionBar

        //设置全屏模式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_splash);

        //判断在SharedPreferences中存在的登录信息，则跳转到MainActivity，否则跳转到LoginActivity
        SharedPreferences sp = getSharedPreferences("AUTO_LOGIN", Context.MODE_PRIVATE);

        String username = sp.getString("username","");
        String password = sp.getString("password","");

        //应该判断Token，目前没有网络请求，暂时使用判断用户名、密码代替
        Intent intent = null;
        if (username != null && username.length() > 0 &&
                password != null && password.length() > 0){
            //MainActivity
            intent = new Intent(this, MainActivity.class);
        }else {
            //LoginActivity
            intent = new Intent(this, LoginActivity.class);
        }

        //启动Activity
        startActivity(intent);

        finish();//避免后退回来
    }
}
```







```java
package com.example.areturn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      //在设置布局之前，隐藏下ActionBar
      //设置全屏模式
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //去掉标题行
      supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


      setContentView(R.layout.activity_splash);
      //设置App运行后3秒延迟跳转到LoginActivity页面，登录界面
      handler.sendEmptyMessageDelayed(0,3000);

//        //判断在SharedPreferences中存在的登录信息，则跳转到MainActivity，否则跳转到LoginActivity
//        SharedPreferences sp = getSharedPreferences("AUTO_LOGIN", Context.MODE_PRIVATE);
//
//        String username = sp.getString("username","");
//        String password = sp.getString("password","");
//
//
//        //应该判断Token，目前没有网络请求，暂时使用判断用户名、密码代替
//        Intent intent = null;
//        if ((username != null && username.length() > 0 )&&
//                (password != null && password.length() > 0)){
//            //MainActivity
//            intent = new Intent(this, MainActivity.class);
//        }else {
//            //LoginActivity
//            intent = new Intent(this, LoginActivity.class);
//        }
  }
  
  
  //设置App运行3秒画面函数
  @SuppressLint("HandlerLeak")
  private final Handler handler = new Handler() {
      @Override
      public void handleMessage(Message msg) {
          getHome();
          super.handleMessage(msg);
      }
  };

  public void getHome(){
      //判断在SharedPreferences中存在的登录信息，则跳转到MainActivity，否则跳转到LoginActivity
      SharedPreferences sp = getSharedPreferences("AUTO_LOGIN", Context.MODE_PRIVATE);

      String username = sp.getString("username","");
      String password = sp.getString("password","");

      //应该判断Token，目前没有网络请求，暂时使用判断用户名、密码代替
      Intent intent = null;
      if ((username != null && username.length() > 0 )&&
              (password != null && password.length() > 0)){
          //MainActivity
          intent = new Intent(SplashActivity.this, MainActivity.class);
      }else {
          //LoginActivity
          intent = new Intent(this, LoginActivity.class);
      }
      startActivity(intent);
      finish();
  }
}
```

参考文献：[Android实现欢迎界面停留3秒效果](https://cloud.tencent.com/developer/article/1738633#:~:text=Android实现欢迎界面停留3秒效果%201%200.写在前面%20在这篇教程中来实现一个类似于微信的的延迟3秒再进入主界面的效果%E3%80%82%202%201.项目准备%20先新建一个空的android项目%E3%80%82,里面只自带一个MainActivity，首先我们再新建一个Activity叫做WelcomeActivity继承自Activity%E3%80%82%20Activity代码如下：%20...%203%202.总结%20在这里主要利用了android.os.Handler的消息的延迟发送以及处理%E3%80%82%20以上就是本文的全部内容，希望对大家的学习有所帮助%E3%80%82)





退出登录，清空文件配置

```java
private void logout() {
      SharedPreferences sp = getSharedPreferences("AUTO_LOGIN", Context.MODE_PRIVATE);
      SharedPreferences.Editor edit = sp.edit();
      edit.clear();
      edit.commit();
  }
```



ListView

![image-20230503194615043](https://p.ipic.vip/4c64ov.png)



![image-20230503194749179](/Users/zechaowei/Library/Application%20Support/typora-user-images/image-20230503194749179.png)





## 修改标题栏中的文字

<img src="https://p.ipic.vip/qb3cw1.png" alt="image-20230506211435079" style="zoom:33%;" />

具体步骤：

- 在`AndroidManifest.xml`文件中，修改标签`android:label="xxxx"`其中xxxx填写文字即可
- 将对应的Activity中注释掉去掉标题行的代码即可

具体操作：

![](/Users/zechaowei/Documents/011-Photos/000-Coding%20%7C%20%E5%90%84%E7%A7%8D%E4%BB%A3%E7%A0%81%E6%88%AA%E5%9B%BE/002-Android/%E4%BF%AE%E6%94%B9%E6%A0%87%E9%A2%98%E6%A0%8F%E6%96%87%E5%AD%97.gif)



## 菜单设置

渲染菜单需要重写Activity的`onCreateOptionsMenu`方法

```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    //将资源文件(xml形式的菜单内容)渲染到menu中
    getMenuInflater().inflate(R.menu.usual,menu);
    return true;
}
```

整体代码：

```java
package com.example.areturn.Home;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.areturn.R;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题行
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_personal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //将资源文件(xml形式的菜单内容)渲染到menu中
        getMenuInflater().inflate(R.menu.usual,menu);
        return true;
    }
}
```



准备一个菜单文件的布局，设置菜单的样式

```XML
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/subPerson"
        android:orderInCategory="100"
        android:title="删除"
        android:icon="@drawable/icon_remove"
        app:showAsAction="always|withText"/>
</menu>
```

![image-20230506231631605](https://p.ipic.vip/mh6ia3.png)

- id：用于在程序中进行判断
- orderInCategory：用于排序
- title：菜单的文本
- icon：菜单的图标
- showAsAction：
  - always：尽量在菜单条目上显示
  - never：不在菜单条目上显示
  - ifRoom：如果控件够就显示在菜单项目上，否则折叠到…
  - withText：需要显示内容（因为默认只显示图标）
  - app:showAsAction属性



### 菜单的点击事件

<font color = red>重写`onOptionsItemSelected`方法</font>

监听菜单选中的事件使用

```java
//重写方法，监听菜单的选中
@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(item.getItemId() == R.id.deletePerson){
        //TODO 发起网络请求
        Toast.makeText(this, "删除数据", Toast.LENGTH_SHORT).show();
        //删除后关闭页面
        finish();
    }
    return true;
}
```



### Toast

```java
Toast.makeText(this, "删除数据", Toast.LENGTH_SHORT).show();
```

makeText：静态方法，show()展示Toast（吐司）

- Content this
- text：显示的文本
- duration：持续的时间
  - Toast.LENGTH_LONG：显示三秒
  - Toast.LENGTH_SHORT：显示两秒
- show()：展示吐司





### 设置返回键

返回键的设置要在setContentView()函数之前

```java
//设置ActionBar
ActionBar actionBar = getSupportActionBar();
//设置后退按钮
actionBar.setDisplayHomeAsUpEnabled(true);	//实际上是在菜单项目上显示菜单（在左边显示）
```

![image-20230506224517103](https://p.ipic.vip/ndba54.png)

返回键的事件监听

<font color = red>返回键的id，系统默认为`android.R.id.home`</font>

```java
//重写方法，监听菜单的选中
@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(item.getItemId() == R.id.deletePerson){
        //TODO 发起网络请求
        Toast.makeText(this, "删除数据", Toast.LENGTH_SHORT).show();
        //删除后关闭页面
        finish();
    }else if (item.getItemId() == android.R.id.home){		//返回键的事件监听
        Toast.makeText(this,"返回上一页",Toast.LENGTH_SHORT).show();
        finish();
    }
    return true;
}
```





## 实现选项卡

`Shift+F6`：快速修改文件名

- 之前都是使用ActioBar添加Tab

- 现在使用使用`TabLayout`实现选项卡

  - 添加依赖`implementation 'com.google.android.material:material:1.8.0'`，部分版本可能没有添加依赖

    ![image-20230508001011157](https://p.ipic.vip/q73uup.png)

定义组件

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">
  
  
  <!--设置选项卡的样式-->
    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tab"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:background="#666666"
        app:tabTextColor="#FFFFFF"
        app:tabIndicatorColor="#FFFFFF">
    </com.google.android.material.tabs.TabLayout>

</RelativeLayout>
```



```java
package com.example.areturn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        //设置选项卡
        tabLayout = findViewById(R.id.tab);
        //填充选项内容
        tabLayout.addTab(tabLayout.newTab().setText("心间广场"));
        tabLayout.addTab(tabLayout.newTab().setText("心理咨询"));
        tabLayout.addTab(tabLayout.newTab().setText("心理知识"));
        tabLayout.addTab(tabLayout.newTab().setText("心理小憩"));
    }
}
```

效果演示：

<img src="https://p.ipic.vip/i10ow2.png" alt="image-20230508002845311" style="zoom:50%;" />

- tabTextColor：文本的颜色
- tabIndicatorColor：下划线的颜色

操作的时候可能会报错`tab<com.google.android.material.tabs.abllayout>:no speakable text present`，但是还是可以运行，强迫症的可以添加如下代码<font color = red>`tools:ignore="SpeakableTextPresentCheck"`</font>即可解决报错问题。

<img src="https://p.ipic.vip/yoq0jz.png" alt="image-20230508003448478" style="zoom:33%;" />





### ViewPager

使用ViewPager实现页面的左右滑动

- 在布局文件中设置ViewPager的组件
- 实现ViewPager的动态滑动更多效果(实现PagerAdapter)
  - getCount：
  - instantiateItem：
  - destoryItem：
  - isViewFromObject：



```java
package com.example.areturn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //设置选项卡
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
        //填充选项内容
        tabLayout.addTab(tabLayout.newTab().setText("心间广场"));
        tabLayout.addTab(tabLayout.newTab().setText("心理咨询"));
        tabLayout.addTab(tabLayout.newTab().setText("心理知识"));
        tabLayout.addTab(tabLayout.newTab().setText("心理小憩"));

        //提前创建好三个 TextView
        List<TextView> viewPagerItem = new ArrayList();
        for (int i = 0; i < 3; i++) {
            TextView tv = new TextView(this);
            tv.setText("第" + i + "个页面内容");
            tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //设置内容居中（垂直居中和水平居中）
//            tv.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL); 等价于tv.setGravity(Gravity.CENTER);
            tv.setGravity(Gravity.CENTER);
            viewPagerItem.add(tv);
        }

        viewPager.setAdapter(new PagerAdapter() {
            //告诉ViewPager一共有几个页面--核心函数
            @Override
            public int getCount() {
                return viewPagerItem.size();
            }

          	//官方书写，return view == object;
            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            //用于真正设置具体某一个位置的视图--核心函数
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                //container --ViewPager
                TextView item = viewPagerItem.get(position);
                container.addView(item);
                return item;
            }
          	
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
              ViewPager vp = (ViewPager) container;
                vp.removeView((View) object);
                System.out.println(container);
                System.out.println(position);
                System.out.println(object);
            }
        });
    }
}
```







### Fragment

- 实现Fragment

- 实现Fragment的生命周期函数

  - onCreateView：当需要显示当前Fragment的时候执行此方法，用于创建视图。

- ```java
  package com.example.areturn;
  
  import android.os.Bundle;
  import android.view.Gravity;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  import android.widget.TextView;
  
  import androidx.annotation.NonNull;
  import androidx.annotation.Nullable;
  import androidx.fragment.app.Fragment;
  
  public class FragmentOne extends Fragment {
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          TextView tv = new TextView(getActivity());
          tv.setText("心间");
          tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
          tv.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
          tv.setGravity(Gravity.CENTER);
          return tv;
      }
  }
  ```





### 将Fragment与ViewPager关联

使用FragmentPagerAdapter适配器管理Fragment

- 首先创建多个Fragment用于在ViewPager中滑动展示

- 使用FragmentPagerAdapter适配器管理Fragment的声明周期

  - 需要给适配器传递一个FragmentManager fm（从当前的Activity中获取）

- ```java
  class MyFragmentPagerAdapter extends FragmentPagerAdapter {
      //构造器
      public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
          super(fm, MyFragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
      }
  
      @NonNull
      @Override
      public Fragment getItem(int position) {
          return fragments.get(position);
      }
  
      @Override
      public int getCount() {
          return fragments.size();
      }
  }
  ```



源代码解析

Java代码

```java
package com.example.areturn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  
    private TabLayout tabLayout;
    private ViewPager viewPager;

    List<Fragment> fragments = new ArrayList<>();
    String tabTitles[] = {"心间广场","心理咨询","心理知识","心理小憩"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //设置选项卡
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        //设置ViewPager之后，Tab的选项 内容由ViewPager（适配器）提供
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < 4; i++) {
            fragments.add(new FragmentOne());
        }

        //FragmentManger
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        //构造器
        public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, MyFragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

      	//展示和viewpager上面tltle的展示文字的效果
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
```

布局文件

> 修改了`activity_main`的布局方式，有RelativeLayout修改为LinearLayout，防止覆盖tab的布局

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity"
    android:padding="10dp">

    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tab"
        android:layout_width="match_parent"
        android:layout_height="70dp"
        android:background="#D6FD51"
        app:tabTextColor="#3366CC"
        app:tabIndicatorColor="#000000"
        tools:ignore="SpeakableTextPresentCheck">

    </com.google.android.material.tabs.TabLayout>

    <!--设置ViewPager页面-->
    <androidx.viewpager.widget.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1">		<!--设置权重为1，使得每个均匀分布，将“缝隙”填满-->

    </androidx.viewpager.widget.ViewPager>

</LinearLayout>
```

### 将ViewPager和TabLayout关联

关联之后TabLayout中题目（Item）有ViewPager的适配器方法getPageTitle提供

```java
//设置ViewPager之后，Tab的选项 内容由ViewPager（适配器）提供
tabLayout.setupWithViewPager(viewPager);
```







### 使用布局管理器渲染视图填充Fragment

创建一个文件布局`fragment_one.xml`：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

<!--    TextView tv = new TextView(getActivity());-->
<!--    tv.setText("心间广场");-->
<!--    tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);-->
<!--    tv.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);-->
<!--    //设置内容居中（垂直居中和水平居中）-->
<!--    tv.setGravity(Gravity.CENTER);-->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="center"
        android:text="心间广场"
        android:textSize="50sp"
        android:background="#FF00FF"/>

</LinearLayout>
```



在Fragment中使用xml文件渲染

`fragmentone.java`文件：

```java
package com.example.areturn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {
  
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      	//核心代码
        return inflater.inflate(R.layout.fragment_one,container,false);
    }
}
```

container：即将要把布局添加到的父容器（父组件）ViewPager，传递给inflate方法的作用是用于计算容器布局大小



### 使用Fragment

`activity_main.java`函数：

```java
package com.example.areturn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.areturn.Fragment.FragmentFour;
import com.example.areturn.Fragment.FragmentOne;
import com.example.areturn.Fragment.FragmentThree;
import com.example.areturn.Fragment.FragmentTwo;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    List<Fragment> fragments = new ArrayList<>();
    String tabTitles[] = {"心间广场","心理咨询","心理知识","心理小憩"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      
        super.onCreate(savedInstanceState);
        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //设置选项卡
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        //设置ViewPager之后，Tab的选项 内容由ViewPager（适配器）提供
        tabLayout.setupWithViewPager(viewPager);


//        for (int i = 0; i < 4; i++) {
//            fragments.add(new FragmentOne());
//        }

      	//设置每一个fragment页面，并实现对应的Fragment.java程序
      	//取消使用for循环，实现独立界面
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentThree());
        fragments.add(new FragmentFour());


        //FragmentManger
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        //构造器
        public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, MyFragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
```







## 设置字体粗细

要在 XML 中设置文本的粗细，您可以在相应的文本视图组件中使用 `android:textStyle` 属性。该属性接受以下三个值：

- `normal`：普通文本风格
- `bold`：粗体风格
- `italic`：斜体风格

例如，以下代码将 TextView 的文本设置为粗体：

```xml
xml复制代码<TextView
    android:text="这是粗体文本"
    android:textStyle="bold"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

您也可以同时使用多个风格。例如，如果您想要使用粗体和斜体来显示文本，可以使用以下代码：

```xml
xml复制代码<TextView
    android:text="这是粗体和斜体的文本"
    android:textStyle="bold|italic"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

在这个例子中，`android:textStyle` 属性被设置为 `bold|italic`，其中 `|` 符号用于分隔多个风格。然后，文本视图将使用粗体和斜体一起来显示文本。









## 实现刷视频功能

在 Android 中，我们可以使用 ScrollView 或 NestedScrollView 来实现可以滚动的容器，并使用 ImageView 控件来插入图片。在接下来的代码示例中，我们将使用 NestedScrollView 和 Glide 图片加载库来实现这个功能。

首先，在你的 `build.gradle` 文件中添加 Glide 的依赖：

```
gradle复制代码implementation 'com.github.bumptech.glide:glide:4.11.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
```

然后，在你的布局文件中添加以下代码：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:andorid="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">


    <androidx.core.widget.NestedScrollView
        android:id="@+id/scrollView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#AAFFCC">

        <LinearLayout
            android:id="@+id/container"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <!-- 添加 20 张图片 -->
            <ImageView
                android:id="@+id/image1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:adjustViewBounds="true"
                android:scaleType="centerCrop"
                android:src="@drawable/game_128" />

            <ImageView
                android:id="@+id/image2"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:adjustViewBounds="true"
                android:scaleType="centerCrop"
                android:src="@drawable/icon_discussion_64"
                android:layout_marginTop="5dp"/>

            <ImageView
                android:id="@+id/image3"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:adjustViewBounds="true"
                android:scaleType="centerCrop"
                android:src="@drawable/game_128"
                android:layout_marginTop="5dp"/>


            <ImageView
                android:id="@+id/image4"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:adjustViewBounds="true"
                android:scaleType="centerCrop"
                android:src="@drawable/icon_discussion_64"
                android:layout_marginTop="5dp"/>

            <!-- 将其余的图片省略 -->

        </LinearLayout>

    </androidx.core.widget.NestedScrollView>
</LinearLayout>
```

























## 杂项记录

**APP页面内部的标准术语**

![img](https://pic3.zhimg.com/v2-0c52b2b785248fd749f94dc9bcd142d2_r.jpg)

![img](https://pic2.zhimg.com/v2-8aded0f210830a4ca422a2976160d84d_r.jpg)

![img](https://pic3.zhimg.com/80/v2-7ce4e2b5a3ceed501ad172b62c58dd2e_1440w.webp)

![img](https://pic1.zhimg.com/80/v2-b4b14d719f0c0cb1416093ba9d193300_1440w.webp)

![img](https://pic3.zhimg.com/80/v2-058f4b2385868b596a15df7109a6edb2_1440w.webp)











## ListView

![image-20230504205701038](https://p.ipic.vip/8d40ze.png)



> 四大组件：
>
> Activity：
>
> 活动状态：
>
> ![image-20230430165930965](https://p.ipic.vip/yxs7l3.png)
>
> ![image-20230430171141100](https://p.ipic.vip/am9ntu.png)

如何点击退出：[最终点击退出教程](https://www.bilibili.com/video/BV13r4y1b7KN/?spm_id_from=pageDriver&vd_source=26358c0abc7c39ff8f3329b40c3818d5)

签到功能：[Android日历签到，超级简单的实现方式](
