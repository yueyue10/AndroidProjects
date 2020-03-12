# JniDemo

初始化

### CMake编译：
* 修改app.gradle中的cmake版本为3.6.0
* 删除build相关文件夹
* 执行gradle中的externalNativeBuildDebug
* cmake代码介绍
```
设置变量 set(var hello)  set(var 111)  
引用变量 message(${var}) 
CMakeLists文件所在位置  message(${CMAKE_CURRENT_LIST_FILE})
CMakeLists文件所在文件夹位置  message(${CMAKE_CURRENT_LIST_DIR})
```
```
条件判断：
IF (TRUE)
   message("this is true")
ENDIF ()
```
```
动态库的引用：
add_library(
        people-lib
        SHARED
        people/people.cpp)      将c++文件编译成动态库
target_link_libraries()     将动态库和动态库进行关联        
```