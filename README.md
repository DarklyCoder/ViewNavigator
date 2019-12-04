# ViewNavigator

视图切换简易框架

## 项目起源

&ensp;&ensp;&ensp;&ensp;在开发 `双屏机` 相关项目的过程中，遇到了副屏的概念，副屏是基于 `Presentation` 显示的，而 `Presentation` 本质上就是一个 `Dialog` 。

&ensp;&ensp;&ensp;&ensp;为了项目管理简单，且不频繁的切换副屏，采取了 `单 Activity 单 Presentation` 模式，即整个项目中只存在一个 `Activity` 和一个 `Presentation`。此项目就是为了管理副屏界面的显示而诞生的，本质上就是 `View的切换管理`。

&ensp;&ensp;&ensp;&ensp;此框架也适用于简单的view切换场景。

## 使用

1. 在 build.gradle 中 添加如下内容：

    ``` shell
    implementation 'com.darklycoder.lib:ViewNavigator:1.0.3'
    implementation 'com.darklycoder.lib:ViewNavigator-Compiler:1.0.3'
    kapt 'com.darklycoder.lib:ViewNavigator-Compiler:1.0.3'
    ```

2. 自定义界面

    继承 `PageView` 实现自定义界面，如下：
    
    ``` kotlin
    @Navigator(path = Paths.PATH_INIT)
    class InitView(context: Context) : PageView(context) {

        init {
            bindContainerView(this)
            setBackgroundColor(Color.WHITE)
        }

    }
    ```
    * `Navigator`： 使用注解指定路径，后续界面切换都是根据比设定的路径；
    
    * `bindContainerView` ：如果自定义view内部需要切换子界面，则需要调用绑定具体的容器view；
    
3. 初始化

    ```
    MultiViewNavigator.initPaths(ViewPaths.getPaths())
    ```
    
    * `ViewPaths` 编译后会自动生成
    
## api

| 操作  | 函数|
|---|---|
|  添加导航器 |MultiViewNavigator.add(ViewNavigator, TAG) |
|  跳转 |MultiViewNavigator.jump(Path, TAG) |
|  跳转 |MultiViewNavigator.jump(ViewIntent, TAG) |
|  返回 |MultiViewNavigator.back(DEEP, TAG) |
|  关闭指定界面 |MultiViewNavigator.finishByKey(Path, TAG) |
|  全部关闭 |MultiViewNavigator.finish(TAG) |
|  显示 |MultiViewNavigator.onShow(TAG) |
|  隐藏 |MultiViewNavigator.onHide(TAG) |

## TODO 
 
* 动画处理
* 手势处理
* ViewModel支持
* 老项目兼容

## Demo

参考示例代码[app](https://github.com/DarklyCoder/ViewNavigator/tree/master/app)



    






