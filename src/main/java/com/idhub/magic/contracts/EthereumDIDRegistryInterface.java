package com.idhub.magic.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class EthereumDIDRegistryInterface extends Contract {
    private static final String BINARY = "";

    public static final String FUNC_REVOKEATTRIBUTE = "revokeAttribute";

    public static final String FUNC_SETATTRIBUTESIGNED = "setAttributeSigned";

    public static final String FUNC_CHANGEOWNERSIGNED = "changeOwnerSigned";

    public static final String FUNC_VALIDDELEGATE = "validDelegate";

    public static final String FUNC_SETATTRIBUTE = "setAttribute";

    public static final String FUNC_REVOKEDELEGATE = "revokeDelegate";

    public static final String FUNC_IDENTITYOWNER = "identityOwner";

    public static final String FUNC_REVOKEDELEGATESIGNED = "revokeDelegateSigned";

    public static final String FUNC_ADDDELEGATESIGNED = "addDelegateSigned";

    public static final String FUNC_ADDDELEGATE = "addDelegate";

    public static final String FUNC_REVOKEATTRIBUTESIGNED = "revokeAttributeSigned";

    public static final String FUNC_CHANGEOWNER = "changeOwner";

    @Deprecated
    protected EthereumDIDRegistryInterface(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EthereumDIDRegistryInterface(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EthereumDIDRegistryInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EthereumDIDRegistryInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> revokeAttribute(String identity, byte[] name, byte[] value) {
        final Function function = new Function(
                FUNC_REVOKEATTRIBUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.web3j.abi.datatypes.DynamicBytes(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setAttributeSigned(String identity, BigInteger sigV, byte[] sigR, byte[] sigS, byte[] name, byte[] value, BigInteger validity) {
        final Function function = new Function(
                FUNC_SETATTRIBUTESIGNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Uint8(sigV), 
                new org.web3j.abi.datatypes.generated.Bytes32(sigR), 
                new org.web3j.abi.datatypes.generated.Bytes32(sigS), 
                new org.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.web3j.abi.datatypes.DynamicBytes(value), 
                new org.web3j.abi.datatypes.generated.Uint256(validity)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeOwnerSigned(String identity, BigInteger sigV, byte[] sigR, byte[] sigS, String newOwner) {
        final Function function = new Function(
                FUNC_CHANGEOWNERSIGNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Uint8(sigV), 
                new org.web3j.abi.datatypes.generated.Bytes32(sigR), 
                new org.web3j.abi.datatypes.generated.Bytes32(sigS), 
                new org.web3j.abi.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> validDelegate(String identity, byte[] delegateType, String delegate) {
        final Function function = new Function(FUNC_VALIDDELEGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Bytes32(delegateType), 
                new org.web3j.abi.datatypes.Address(delegate)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> setAttribute(String identity, byte[] name, byte[] value, BigInteger validity) {
        final Function function = new Function(
                FUNC_SETATTRIBUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.web3j.abi.datatypes.DynamicBytes(value), 
                new org.web3j.abi.datatypes.generated.Uint256(validity)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> revokeDelegate(String identity, byte[] delegateType, String delegate) {
        final Function function = new Function(
                FUNC_REVOKEDELEGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Bytes32(delegateType), 
                new org.web3j.abi.datatypes.Address(delegate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> identityOwner(String identity) {
        final Function function = new Function(FUNC_IDENTITYOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> revokeDelegateSigned(String identity, BigInteger sigV, byte[] sigR, byte[] sigS, byte[] delegateType, String delegate) {
        final Function function = new Function(
                FUNC_REVOKEDELEGATESIGNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Uint8(sigV), 
                new org.web3j.abi.datatypes.generated.Bytes32(sigR), 
                new org.web3j.abi.datatypes.generated.Bytes32(sigS), 
                new org.web3j.abi.datatypes.generated.Bytes32(delegateType), 
                new org.web3j.abi.datatypes.Address(delegate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addDelegateSigned(String identity, BigInteger sigV, byte[] sigR, byte[] sigS, byte[] delegateType, String delegate, BigInteger validity) {
        final Function function = new Function(
                FUNC_ADDDELEGATESIGNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Uint8(sigV), 
                new org.web3j.abi.datatypes.generated.Bytes32(sigR), 
                new org.web3j.abi.datatypes.generated.Bytes32(sigS), 
                new org.web3j.abi.datatypes.generated.Bytes32(delegateType), 
                new org.web3j.abi.datatypes.Address(delegate), 
                new org.web3j.abi.datatypes.generated.Uint256(validity)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addDelegate(String identity, byte[] delegateType, String delegate, BigInteger validity) {
        final Function function = new Function(
                FUNC_ADDDELEGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Bytes32(delegateType), 
                new org.web3j.abi.datatypes.Address(delegate), 
                new org.web3j.abi.datatypes.generated.Uint256(validity)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> revokeAttributeSigned(String identity, BigInteger sigV, byte[] sigR, byte[] sigS, byte[] name, byte[] value) {
        final Function function = new Function(
                FUNC_REVOKEATTRIBUTESIGNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Uint8(sigV), 
                new org.web3j.abi.datatypes.generated.Bytes32(sigR), 
                new org.web3j.abi.datatypes.generated.Bytes32(sigS), 
                new org.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.web3j.abi.datatypes.DynamicBytes(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeOwner(String identity, String newOwner) {
        final Function function = new Function(
                FUNC_CHANGEOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static EthereumDIDRegistryInterface load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EthereumDIDRegistryInterface(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EthereumDIDRegistryInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EthereumDIDRegistryInterface(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EthereumDIDRegistryInterface load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EthereumDIDRegistryInterface(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EthereumDIDRegistryInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EthereumDIDRegistryInterface(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EthereumDIDRegistryInterface> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EthereumDIDRegistryInterface.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EthereumDIDRegistryInterface> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EthereumDIDRegistryInterface.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<EthereumDIDRegistryInterface> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EthereumDIDRegistryInterface.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EthereumDIDRegistryInterface> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EthereumDIDRegistryInterface.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
