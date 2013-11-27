package cmput301.f13t01.elasticsearch;

import java.util.Collection;

public class Hits<T> {

	int total;
	double max_score;
	Collection<ElasticSearchResponse<T>> hits;
	public Collection<ElasticSearchResponse<T>> getHits() {
		return hits;
	}
	
	public String toString() {
		return (super.toString() + "," + total + "," + max_score + "," + hits);
	}
	
	public int getTotal() {
		return total;
	}
	
}
