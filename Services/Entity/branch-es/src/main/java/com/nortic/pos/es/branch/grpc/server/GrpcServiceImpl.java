package com.nortic.pos.es.branch.grpc.server;

import com.nortic.pos.es.branch.grpc.gen.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class GrpcServiceImpl extends BranchServiceGrpc.BranchServiceImplBase {
    @Override
    public void createBranch(Store request, StreamObserver<Store> responseObserver) {
        super.createBranch(request, responseObserver);
    }

    @Override
    public void getBranchById(BranchIdRequest request, StreamObserver<Branch> responseObserver) {
        super.getBranchById(request, responseObserver);
    }

    @Override
    public void updateBranch(Branch request, StreamObserver<Branch> responseObserver) {
        super.updateBranch(request, responseObserver);
    }

    @Override
    public void deleteBranch(BranchIdRequest request, StreamObserver<DeleteResponse> responseObserver) {
        super.deleteBranch(request, responseObserver);
    }

    @Override
    public void softDeleteBranch(BranchIdRequest request, StreamObserver<DeleteResponse> responseObserver) {
        super.softDeleteBranch(request, responseObserver);
    }

    @Override
    public void changeBranchStatus(ChangeStatusRequest request, StreamObserver<BranchResponse> responseObserver) {
        super.changeBranchStatus(request, responseObserver);
    }

    @Override
    public void listBranchesByStore(ListBranchesRequest request, StreamObserver<ListBranchesResponse> responseObserver) {
        super.listBranchesByStore(request, responseObserver);
    }
}
