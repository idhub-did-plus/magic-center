package com.idhub.magic.center.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import io.reactivex.Flowable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class EthereumClaimsRegistry extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506102ed806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80639155b01a1461005c5780639918925d14610081578063c7508ec7146100b3578063e1661eff146100e9578063e6d970aa14610131575b600080fd5b61007f6004803603604081101561007257600080fd5b5080359060200135610167565b005b61007f6004803603606081101561009757600080fd5b506001600160a01b038135169060208101359060400135610176565b61007f600480360360608110156100c957600080fd5b506001600160a01b038135811691602081013590911690604001356101e5565b61011f600480360360608110156100ff57600080fd5b506001600160a01b03813581169160208101359091169060400135610266565b60408051918252519081900360200190f35b61011f6004803603606081101561014757600080fd5b506001600160a01b03813581169160208101359091169060400135610296565b610172338383610176565b5050565b336000818152602081815260408083206001600160a01b0388168085529083528184208785528352928190208590558051858152429281019290925280518694927f8c9f893548e8429f9352aba6698e6f4dca2e390604f6e8c5881a7a505d94ae5092908290030190a4505050565b336001600160a01b038416146101fa57600080fd5b6001600160a01b038084166000908152602081815260408083209386168084529382528083208584528252808320929092558151428152915184939233927f7bc046ef9f2fccd4c99778d7e0fbed67ddb16e63e1a266894769de9516b8039a92918290030190a4505050565b6001600160a01b039283166000908152602081815260408083209490951682529283528381209181529152205490565b600060208181529381526040808220855292815282812090935282529020548156fea265627a7a72305820a383d33ac7421044b91e6e54332bce44769ea74b7fa27d2e8e732f9dc3d5d6e964736f6c634300050a0032";

    public static final String FUNC_SETSELFCLAIM = "setSelfClaim";

    public static final String FUNC_SETCLAIM = "setClaim";

    public static final String FUNC_REMOVECLAIM = "removeClaim";

    public static final String FUNC_GETCLAIM = "getClaim";

    public static final String FUNC_REGISTRY = "registry";

    public static final Event CLAIMSET_EVENT = new Event("ClaimSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CLAIMREMOVED_EVENT = new Event("ClaimRemoved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected EthereumClaimsRegistry(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EthereumClaimsRegistry(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EthereumClaimsRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EthereumClaimsRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setSelfClaim(byte[] key, byte[] value) {
        final Function function = new Function(
                FUNC_SETSELFCLAIM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(key), 
                new org.web3j.abi.datatypes.generated.Bytes32(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setClaim(String subject, byte[] key, byte[] value) {
        final Function function = new Function(
                FUNC_SETCLAIM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(subject), 
                new org.web3j.abi.datatypes.generated.Bytes32(key), 
                new org.web3j.abi.datatypes.generated.Bytes32(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeClaim(String issuer, String subject, byte[] key) {
        final Function function = new Function(
                FUNC_REMOVECLAIM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(issuer), 
                new org.web3j.abi.datatypes.Address(subject), 
                new org.web3j.abi.datatypes.generated.Bytes32(key)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> getClaim(String issuer, String subject, byte[] key) {
        final Function function = new Function(FUNC_GETCLAIM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(issuer), 
                new org.web3j.abi.datatypes.Address(subject), 
                new org.web3j.abi.datatypes.generated.Bytes32(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> registry(String param0, String param1, byte[] param2) {
        final Function function = new Function(FUNC_REGISTRY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.Address(param1), 
                new org.web3j.abi.datatypes.generated.Bytes32(param2)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public List<ClaimSetEventResponse> getClaimSetEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLAIMSET_EVENT, transactionReceipt);
        ArrayList<ClaimSetEventResponse> responses = new ArrayList<ClaimSetEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ClaimSetEventResponse typedResponse = new ClaimSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.issuer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.subject = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.key = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.value = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.updatedAt = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ClaimSetEventResponse> claimSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ClaimSetEventResponse>() {
            @Override
            public ClaimSetEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLAIMSET_EVENT, log);
                ClaimSetEventResponse typedResponse = new ClaimSetEventResponse();
                typedResponse.log = log;
                typedResponse.issuer = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.subject = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.key = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.value = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.updatedAt = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ClaimSetEventResponse> claimSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLAIMSET_EVENT));
        return claimSetEventFlowable(filter);
    }

    public List<ClaimRemovedEventResponse> getClaimRemovedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLAIMREMOVED_EVENT, transactionReceipt);
        ArrayList<ClaimRemovedEventResponse> responses = new ArrayList<ClaimRemovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ClaimRemovedEventResponse typedResponse = new ClaimRemovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.issuer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.subject = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.key = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.removedAt = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ClaimRemovedEventResponse> claimRemovedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ClaimRemovedEventResponse>() {
            @Override
            public ClaimRemovedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLAIMREMOVED_EVENT, log);
                ClaimRemovedEventResponse typedResponse = new ClaimRemovedEventResponse();
                typedResponse.log = log;
                typedResponse.issuer = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.subject = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.key = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.removedAt = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ClaimRemovedEventResponse> claimRemovedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLAIMREMOVED_EVENT));
        return claimRemovedEventFlowable(filter);
    }

    @Deprecated
    public static EthereumClaimsRegistry load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EthereumClaimsRegistry(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EthereumClaimsRegistry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EthereumClaimsRegistry(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EthereumClaimsRegistry load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EthereumClaimsRegistry(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EthereumClaimsRegistry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EthereumClaimsRegistry(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EthereumClaimsRegistry> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EthereumClaimsRegistry.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EthereumClaimsRegistry> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EthereumClaimsRegistry.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<EthereumClaimsRegistry> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EthereumClaimsRegistry.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EthereumClaimsRegistry> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EthereumClaimsRegistry.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ClaimSetEventResponse {
        public Log log;

        public String issuer;

        public String subject;

        public byte[] key;

        public byte[] value;

        public BigInteger updatedAt;
    }

    public static class ClaimRemovedEventResponse {
        public Log log;

        public String issuer;

        public String subject;

        public byte[] key;

        public BigInteger removedAt;
    }
}
