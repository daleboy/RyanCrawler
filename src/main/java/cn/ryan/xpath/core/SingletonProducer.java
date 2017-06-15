package cn.ryan.xpath.core;

public class SingletonProducer {
    private static SingletonProducer producer = new SingletonProducer();
    private AxisSelector axisSelector = new AxisSelector();
    private Functions functions =new Functions();

    public static SingletonProducer getInstance() {
        return producer;
    }

    public AxisSelector getAxisSelector() {
        return axisSelector;
    }

    public Functions getFunctions() {
        return functions;
    }

    private SingletonProducer() {
    }
}
