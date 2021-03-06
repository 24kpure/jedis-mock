package com.github.lemonj.jedismock.operations;

import com.github.lemonj.jedismock.server.Response;
import com.github.lemonj.jedismock.server.Slice;
import com.github.lemonj.jedismock.storage.RedisBase;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RO_hgetall extends AbstractRedisOperation {
    public RO_hgetall(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice response() {
        Slice hash = params().get(0);

        Map<Slice, Slice> fieldAndValueMap = base().getFieldsAndValues(hash);
        int arraySize = fieldAndValueMap.size() * 2;
        Slice [] fieldAndValueList = new Slice[arraySize];

        int currentIndex = arraySize - 1;
        for (Map.Entry<Slice, Slice> entry: fieldAndValueMap.entrySet()){
            fieldAndValueList[currentIndex] = Response.bulkString(entry.getValue());
            currentIndex--;

            fieldAndValueList[currentIndex] = Response.bulkString(entry.getKey());
            currentIndex--;
        }

        List<Slice> values = Arrays.asList(fieldAndValueList);
        return Response.array(values);
    }
}
