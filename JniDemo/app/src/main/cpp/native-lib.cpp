#include <jni.h>
#include <string>
#include <people.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_zyj_jnidemo_JNITools_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++ 啊";
    People people;
//    return env->NewStringUTF(hello.c_str());
    return env->NewStringUTF(people.getString().c_str());
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_zyj_jnidemo_JNITools_add(JNIEnv *env, jclass type, jint a, jint b) {

    return a + b;

}
extern "C"
JNIEXPORT jint JNICALL
Java_com_zyj_jnidemo_JNITools_sub(JNIEnv *env, jclass type, jint a, jint b) {

    return a - b;

}
extern "C"
JNIEXPORT jint JNICALL
Java_com_zyj_jnidemo_JNITools_mul(JNIEnv *env, jclass type, jint a, jint b) {

    return a * b;

}
extern "C"
JNIEXPORT jint JNICALL
Java_com_zyj_jnidemo_JNITools_div(JNIEnv *env, jclass type, jint a, jint b) {

    return a / b;

}