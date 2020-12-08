package code.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * PreciseShardingAlgorithmImpl
 *
 * @author wangwenjie
 * @date 2020-12-08
 */
public class PreciseShardingAlgorithmImpl implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        String dbName = "ds";
        Long val = preciseShardingValue.getValue();
        dbName += val;
        for (String each : collection) {
            if (each.equals(dbName)) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
}
