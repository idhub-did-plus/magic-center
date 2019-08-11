package com.idhub.magic.center.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
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
public class IdentityRegistryInterface extends Contract {
    private static final String BINARY = "0x";

    public static final String FUNC_ISSIGNED = "isSigned";

    public static final String FUNC_IDENTITYEXISTS = "identityExists";

    public static final String FUNC_HASIDENTITY = "hasIdentity";

    public static final String FUNC_GETEIN = "getEIN";

    public static final String FUNC_ISASSOCIATEDADDRESSFOR = "isAssociatedAddressFor";

    public static final String FUNC_ISPROVIDERFOR = "isProviderFor";

    public static final String FUNC_ISRESOLVERFOR = "isResolverFor";

    public static final String FUNC_GETIDENTITY = "getIdentity";

    public static final String FUNC_MAPUINT8 = "mapuint8";

    public static final String FUNC_MAPBYTES32 = "mapbytes32";

    public static final String FUNC_ERECOVER = "erecover";

    public static final String FUNC_ENCODE = "encode";

    public static final String FUNC_HASH = "hash";

    public static final String FUNC_CREATEIDENTITY = "createIdentity";

    public static final String FUNC_CREATEIDENTITYDELEGATED = "createIdentityDelegated";

    public static final String FUNC_ADDASSOCIATEDADDRESS = "addAssociatedAddress";

    public static final String FUNC_ADDASSOCIATEDADDRESSDELEGATED = "addAssociatedAddressDelegated";

    public static final String FUNC_REMOVEASSOCIATEDADDRESS = "removeAssociatedAddress";

    public static final String FUNC_REMOVEASSOCIATEDADDRESSDELEGATED = "removeAssociatedAddressDelegated";

    public static final String FUNC_ADDPROVIDERS = "addProviders";

    public static final String FUNC_ADDPROVIDERSFOR = "addProvidersFor";

    public static final String FUNC_REMOVEPROVIDERS = "removeProviders";

    public static final String FUNC_REMOVEPROVIDERSFOR = "removeProvidersFor";

    public static final String FUNC_ADDRESOLVERS = "addResolvers";

    public static final String FUNC_ADDRESOLVERSFOR = "addResolversFor";

    public static final String FUNC_REMOVERESOLVERS = "removeResolvers";

    public static final String FUNC_REMOVERESOLVERSFOR = "removeResolversFor";

    public static final String FUNC_TRIGGERRECOVERYADDRESSCHANGE = "triggerRecoveryAddressChange";

    public static final String FUNC_TRIGGERRECOVERYADDRESSCHANGEFOR = "triggerRecoveryAddressChangeFor";

    public static final String FUNC_TRIGGERRECOVERY = "triggerRecovery";

    public static final String FUNC_TRIGGERDESTRUCTION = "triggerDestruction";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected IdentityRegistryInterface(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IdentityRegistryInterface(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IdentityRegistryInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IdentityRegistryInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Boolean> isSigned(String _address, byte[] messageHash, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(FUNC_ISSIGNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address), 
                new org.web3j.abi.datatypes.generated.Bytes32(messageHash), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> identityExists(BigInteger ein) {
        final Function function = new Function(FUNC_IDENTITYEXISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> hasIdentity(String _address) {
        final Function function = new Function(FUNC_HASIDENTITY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<BigInteger> getEIN(String _address) {
        final Function function = new Function(FUNC_GETEIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> isAssociatedAddressFor(BigInteger ein, String _address) {
        final Function function = new Function(FUNC_ISASSOCIATEDADDRESSFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein), 
                new org.web3j.abi.datatypes.Address(_address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> isProviderFor(BigInteger ein, String provider) {
        final Function function = new Function(FUNC_ISPROVIDERFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein), 
                new org.web3j.abi.datatypes.Address(provider)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> isResolverFor(BigInteger ein, String resolver) {
        final Function function = new Function(FUNC_ISRESOLVERFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein), 
                new org.web3j.abi.datatypes.Address(resolver)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Tuple4<String, List<String>, List<String>, List<String>>> getIdentity(BigInteger ein) {
        final Function function = new Function(FUNC_GETIDENTITY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<Tuple4<String, List<String>, List<String>, List<String>>>(
                new Callable<Tuple4<String, List<String>, List<String>, List<String>>>() {
                    @Override
                    public Tuple4<String, List<String>, List<String>, List<String>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        if(results.isEmpty())
                        	return new Tuple4<String, List<String>, List<String>, List<String>>(
                                    "", 
                                    new ArrayList<String>(),
                                    new ArrayList<String>(),
                                    new ArrayList<String>());
                        return new Tuple4<String, List<String>, List<String>, List<String>>(
                                (String) results.get(0).getValue(), 
                                convertToNative((List<Address>) results.get(1).getValue()), 
                                convertToNative((List<Address>) results.get(2).getValue()), 
                                convertToNative((List<Address>) results.get(3).getValue()));
                    }
                });
    }

    public RemoteCall<BigInteger> mapuint8(BigInteger data) {
        final Function function = new Function(FUNC_MAPUINT8, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(data)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> mapbytes32(byte[] data) {
        final Function function = new Function(FUNC_MAPBYTES32, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(data)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> erecover(String recoveryAddress, String associatedAddress, List<String> providers, List<String> resolvers, BigInteger v, byte[] r, byte[] s, BigInteger timestamp) {
        final Function function = new Function(FUNC_ERECOVER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(recoveryAddress), 
                new org.web3j.abi.datatypes.Address(associatedAddress), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(providers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(resolvers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> encode(String recoveryAddress, String associatedAddress, List<String> providers, List<String> resolvers, BigInteger timestamp) {
        final Function function = new Function(FUNC_ENCODE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(recoveryAddress), 
                new org.web3j.abi.datatypes.Address(associatedAddress), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(providers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(resolvers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> hash(String recoveryAddress, String associatedAddress, List<String> providers, List<String> resolvers, BigInteger timestamp) {
        final Function function = new Function(FUNC_HASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(recoveryAddress), 
                new org.web3j.abi.datatypes.Address(associatedAddress), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(providers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(resolvers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> createIdentity(String recoveryAddress, List<String> providers, List<String> resolvers) {
        final Function function = new Function(
                FUNC_CREATEIDENTITY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(recoveryAddress), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(providers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(resolvers, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> createIdentityDelegated(String recoveryAddress, String associatedAddress, List<String> providers, List<String> resolvers, BigInteger v, byte[] r, byte[] s, BigInteger timestamp) {
        final Function function = new Function(
                FUNC_CREATEIDENTITYDELEGATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(recoveryAddress), 
                new org.web3j.abi.datatypes.Address(associatedAddress), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(providers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(resolvers, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addAssociatedAddress(String approvingAddress, String addressToAdd, BigInteger v, byte[] r, byte[] s, BigInteger timestamp) {
        final Function function = new Function(
                FUNC_ADDASSOCIATEDADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(approvingAddress), 
                new org.web3j.abi.datatypes.Address(addressToAdd), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addAssociatedAddressDelegated(String approvingAddress, String addressToAdd, List<BigInteger> v, List<byte[]> r, List<byte[]> s, List<BigInteger> timestamp) {
        final Function function = new Function(
                FUNC_ADDASSOCIATEDADDRESSDELEGATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(approvingAddress), 
                new org.web3j.abi.datatypes.Address(addressToAdd), 
                new org.web3j.abi.datatypes.generated.StaticArray2<org.web3j.abi.datatypes.generated.Uint8>(
                        org.web3j.abi.datatypes.generated.Uint8.class,
                        org.web3j.abi.Utils.typeMap(v, org.web3j.abi.datatypes.generated.Uint8.class)), 
                new org.web3j.abi.datatypes.generated.StaticArray2<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.datatypes.generated.Bytes32.class,
                        org.web3j.abi.Utils.typeMap(r, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.generated.StaticArray2<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.datatypes.generated.Bytes32.class,
                        org.web3j.abi.Utils.typeMap(s, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.generated.StaticArray2<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(timestamp, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeAssociatedAddress() {
        final Function function = new Function(
                FUNC_REMOVEASSOCIATEDADDRESS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeAssociatedAddressDelegated(String addressToRemove, BigInteger v, byte[] r, byte[] s, BigInteger timestamp) {
        final Function function = new Function(
                FUNC_REMOVEASSOCIATEDADDRESSDELEGATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(addressToRemove), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addProviders(List<String> providers) {
        final Function function = new Function(
                FUNC_ADDPROVIDERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(providers, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addProvidersFor(BigInteger ein, List<String> providers) {
        final Function function = new Function(
                FUNC_ADDPROVIDERSFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(providers, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeProviders(List<String> providers) {
        final Function function = new Function(
                FUNC_REMOVEPROVIDERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(providers, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeProvidersFor(BigInteger ein, List<String> providers) {
        final Function function = new Function(
                FUNC_REMOVEPROVIDERSFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(providers, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addResolvers(List<String> resolvers) {
        final Function function = new Function(
                FUNC_ADDRESOLVERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(resolvers, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addResolversFor(BigInteger ein, List<String> resolvers) {
        final Function function = new Function(
                FUNC_ADDRESOLVERSFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(resolvers, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeResolvers(List<String> resolvers) {
        final Function function = new Function(
                FUNC_REMOVERESOLVERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(resolvers, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeResolversFor(BigInteger ein, List<String> resolvers) {
        final Function function = new Function(
                FUNC_REMOVERESOLVERSFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(resolvers, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> triggerRecoveryAddressChange(String newRecoveryAddress) {
        final Function function = new Function(
                FUNC_TRIGGERRECOVERYADDRESSCHANGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(newRecoveryAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> triggerRecoveryAddressChangeFor(BigInteger ein, String newRecoveryAddress) {
        final Function function = new Function(
                FUNC_TRIGGERRECOVERYADDRESSCHANGEFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein), 
                new org.web3j.abi.datatypes.Address(newRecoveryAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> triggerRecovery(BigInteger ein, String newAssociatedAddress, BigInteger v, byte[] r, byte[] s, BigInteger timestamp) {
        final Function function = new Function(
                FUNC_TRIGGERRECOVERY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein), 
                new org.web3j.abi.datatypes.Address(newAssociatedAddress), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> triggerDestruction(BigInteger ein, List<String> firstChunk, List<String> lastChunk, Boolean resetResolvers) {
        final Function function = new Function(
                FUNC_TRIGGERDESTRUCTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(ein), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(firstChunk, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(lastChunk, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.Bool(resetResolvers)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IdentityRegistryInterface load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IdentityRegistryInterface(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IdentityRegistryInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IdentityRegistryInterface(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IdentityRegistryInterface load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IdentityRegistryInterface(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IdentityRegistryInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IdentityRegistryInterface(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IdentityRegistryInterface> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IdentityRegistryInterface.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IdentityRegistryInterface> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IdentityRegistryInterface.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<IdentityRegistryInterface> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IdentityRegistryInterface.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IdentityRegistryInterface> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IdentityRegistryInterface.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }
}
