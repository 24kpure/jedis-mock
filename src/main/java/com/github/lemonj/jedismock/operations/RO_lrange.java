package com.github.lemonj.jedismock.operations;

import com.github.lemonj.jedismock.server.Response;
import com.github.lemonj.jedismock.server.Slice;
import com.github.lemonj.jedismock.storage.RedisBase;
import com.github.lemonj.jedismock.Utils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

class RO_lrange extends AbstractRedisOperation {
    RO_lrange(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice key = params().get(0);
        LinkedList<Slice> list = getDataFromBase(key, Lists.newLinkedList());

        int start = Utils.convertToInteger(params().get(1).toString());
        int end = Utils.convertToInteger(params().get(2).toString());

        if (start < 0) {
            start = list.size() + start;
            if (start < 0) {
                start = 0;
            }
        }
        if (end < 0) {
            end = list.size() + end;
            if (end < 0) {
                end = 0;
            }
        }
        ImmutableList.Builder<Slice> builder = new ImmutableList.Builder<Slice>();
        for (int i = start; i <= end && i < list.size(); i++) {
            builder.add(Response.bulkString(list.get(i)));
        }
        return Response.array(builder.build());
    }
}
