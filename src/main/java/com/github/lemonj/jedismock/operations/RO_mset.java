package com.github.lemonj.jedismock.operations;

import com.github.lemonj.jedismock.server.Response;
import com.github.lemonj.jedismock.server.Slice;
import com.github.lemonj.jedismock.storage.RedisBase;

import java.util.List;

class RO_mset extends AbstractRedisOperation {
    RO_mset(RedisBase base, List<Slice> params ) {
        super(base, params);
    }

    Slice response() {
        for (int i = 0; i < params().size(); i += 2) {
            base().putValue(params().get(i), params().get(i + 1));
        }
        return Response.OK;
    }
}
