package com.zangs.crow.common.util.ig;

import org.nutz.el.opt.RunMethod;

public interface IdGenerator extends RunMethod {
    String next(String tableName, String prefix) throws Exception;
}
