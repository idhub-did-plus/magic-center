package com.idhub.magic.center.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import org.web3j.abi.TypeReference;
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
public class ERC1056ResolverInterface extends Contract {
    private static final String BINARY = "0x";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_CHANGEOWNER = "changeOwner";

    public static final String FUNC_CHANGEOWNERDELEGATED = "changeOwnerDelegated";

    public static final String FUNC_ADDDELEGATE = "addDelegate";

    public static final String FUNC_ADDDELEGATEDELEGATED = "addDelegateDelegated";

    public static final String FUNC_REVOKEDELEGATE = "revokeDelegate";

    public static final String FUNC_REVOKEDELEGATEDELEGATED = "revokeDelegateDelegated";

    public static final String FUNC_SETATTRIBUTE = "setAttribute";

    public static final String FUNC_SETATTRIBUTEDELEGATED = "setAttributeDelegated";

    public static final String FUNC_REVOKEATTRIBUTE = "revokeAttribute";

    public static final String FUNC_REVOKEATTRIBUTEDELEGATED = "revokeAttributeDelegated";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected ERC1056ResolverInterface(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC1056ResolverInterface(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC1056ResolverInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC1056ResolverInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> initialize(String identity, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(identity), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeOwner(String newOwner) {
        final Function function = new Function(
                FUNC_CHANGEOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeOwnerDelegated(String approvingAddress, String newOwner, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_CHANGEOWNERDELEGATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(approvingAddress), 
                new org.web3j.abi.datatypes.Address(newOwner), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addDelegate(byte[] delegateType, String delegate, BigInteger validity) {
        final Function function = new Function(
                FUNC_ADDDELEGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(delegateType), 
                new org.web3j.abi.datatypes.Address(delegate), 
                new org.web3j.abi.datatypes.generated.Uint256(validity)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addDelegateDelegated(String approvingAddress, byte[] delegateType, String delegate, BigInteger validity, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_ADDDELEGATEDELEGATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(approvingAddress), 
                new org.web3j.abi.datatypes.generated.Bytes32(delegateType), 
                new org.web3j.abi.datatypes.Address(delegate), 
                new org.web3j.abi.datatypes.generated.Uint256(validity), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> revokeDelegate(byte[] delegateType, String delegate) {
        final Function function = new Function(
                FUNC_REVOKEDELEGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(delegateType), 
                new org.web3j.abi.datatypes.Address(delegate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> revokeDelegateDelegated(String approvingAddress, byte[] delegateType, String delegate, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_REVOKEDELEGATEDELEGATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(approvingAddress), 
                new org.web3j.abi.datatypes.generated.Bytes32(delegateType), 
                new org.web3j.abi.datatypes.Address(delegate), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setAttribute(byte[] name, byte[] value, BigInteger validity) {
        final Function function = new Function(
                FUNC_SETATTRIBUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.web3j.abi.datatypes.DynamicBytes(value), 
                new org.web3j.abi.datatypes.generated.Uint256(validity)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setAttributeDelegated(String approvingAddress, byte[] name, byte[] value, BigInteger validity, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_SETATTRIBUTEDELEGATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(approvingAddress), 
                new org.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.web3j.abi.datatypes.DynamicBytes(value), 
                new org.web3j.abi.datatypes.generated.Uint256(validity), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> revokeAttribute(byte[] name, byte[] value) {
        final Function function = new Function(
                FUNC_REVOKEATTRIBUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.web3j.abi.datatypes.DynamicBytes(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> revokeAttributeDelegated(String approvingAddress, byte[] name, byte[] value, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_REVOKEATTRIBUTEDELEGATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(approvingAddress), 
                new org.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.web3j.abi.datatypes.DynamicBytes(value), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ERC1056ResolverInterface load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC1056ResolverInterface(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC1056ResolverInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC1056ResolverInterface(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC1056ResolverInterface load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC1056ResolverInterface(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC1056ResolverInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC1056ResolverInterface(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC1056ResolverInterface> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC1056ResolverInterface.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ERC1056ResolverInterface> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC1056ResolverInterface.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ERC1056ResolverInterface> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ERC1056ResolverInterface.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ERC1056ResolverInterface> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ERC1056ResolverInterface.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }
}
