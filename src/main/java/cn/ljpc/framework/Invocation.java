package cn.ljpc.framework;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 9:11
 * 对数据传输的封装
 */
public class Invocation {

    private String interfaceName; //接口名
    private String methodName;//方法名
    private Object[] params; // 参数列表
    private Class[] paramType;//参数类型列表

    public Invocation(String interfaceName, String methodName, Object[] params, Class[] paramType) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.params = params;
        this.paramType = paramType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class[] getParamType() {
        return paramType;
    }

    public void setParamType(Class[] paramType) {
        this.paramType = paramType;
    }
}
