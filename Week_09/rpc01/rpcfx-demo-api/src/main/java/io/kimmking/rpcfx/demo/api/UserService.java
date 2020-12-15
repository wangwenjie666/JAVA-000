package io.kimmking.rpcfx.demo.api;

import io.kimmking.rpcfx.demo.annontation.Rpc;

@Rpc("http://localhost:8080")
public interface UserService {
    User findById(int id);
}
