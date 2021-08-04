package me.shilu.jreflect;


import me.shilu.jreflect.reflect.ReflectUtil;

public class Java {

    public static Wrapper use(String className) {
        Class<?> cls = ReflectUtil.findMyClass(className, null);
        return new Wrapper(cls);
    }

    public static class Wrapper {
        private Class<?> mClass;
        private Object mObject;
        public Wrapper(){}
        public Wrapper(Class<?> cls) {
            mClass = cls;
        }

        public Wrapper(Object object) {
            mObject = object;
        }

        public Wrapper $new(Object... args) {

            return new Wrapper(ReflectUtil.newInstance(mClass,args));
        }


        public Wrapper call(String methodName, Object... args) {
            if (mObject != null) {
                return new Wrapper(ReflectUtil.callMyMethod(mObject, methodName,args));
            }
            if(mClass!=null){
                return new Wrapper(ReflectUtil.callMyStaticMethod(mClass,methodName,args));
            }
            return new Wrapper();
        }
        public <T> T cast(Class<T> tClass){
            if(mObject!=null){
                return tClass.cast(mObject);
            }
            return null;
        }

        @Override
        public String toString() {
            if (mObject != null) {
                return mObject.toString();
            }
            if(mClass!=null){
                return mClass.getName();
            }
            return "null";
        }
    }
    public static void main(){
        Wrapper obj = Java.use("xxxxx.xxxx.xxxx").call("methodName", "arg");
        System.out.println("obj:"+ obj);
    }
}
