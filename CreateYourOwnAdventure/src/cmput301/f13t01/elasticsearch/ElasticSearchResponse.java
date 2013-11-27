package cmput301.f13t01.elasticsearch;

public class ElasticSearchResponse<T> {
	String _index;
    String _type;
    String _id;
    T _source;
    public T getSource() {
        return _source;
    }
}
