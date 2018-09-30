package com.investment.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Enumeration;


@Slf4j
public class InetUtils {

    public static InetAddress findFirstNonLoopbackAddress() {
        InetAddress result = null;

        try {
            int lowest = 2147483647;
            Enumeration nics = NetworkInterface.getNetworkInterfaces();

            label61:
            while(true) {
                NetworkInterface ifc;
                while(true) {
                    do {
                        if (!nics.hasMoreElements()) {
                            break label61;
                        }

                        ifc = (NetworkInterface)nics.nextElement();
                    } while(!ifc.isUp());

                    log.trace("Testing interface: " + ifc.getDisplayName());
                    if (ifc.getIndex() >= lowest && result != null) {
                        if (result != null) {
                            continue;
                        }
                        break;
                    }

                    lowest = ifc.getIndex();
                    break;
                }

                Enumeration addrs = ifc.getInetAddresses();

                while(addrs.hasMoreElements()) {
                    InetAddress address = (InetAddress)addrs.nextElement();
                    if (address instanceof Inet4Address && !address.isLoopbackAddress() ) {
                        log.trace("Found non-loopback interface: " + ifc.getDisplayName());
                        result = address;
                    }
                }
            }
        } catch (IOException var8) {
            log.error("Cannot get first non-loopback address", var8);
        }

        if (result != null) {
            return result;
        } else {
            try {
                return InetAddress.getLocalHost();
            } catch (UnknownHostException var7) {
                log.warn("Unable to retrieve localhost");
                return null;
            }
        }
    }

    public static int createMachineIdentifier() {
        int machinePiece;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration e = NetworkInterface.getNetworkInterfaces();

            while(e.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface)e.nextElement();
                sb.append(ni.toString());
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    ByteBuffer bb = ByteBuffer.wrap(mac);

                    try {
                        sb.append(bb.getChar());
                        sb.append(bb.getChar());
                        sb.append(bb.getChar());
                    } catch (BufferUnderflowException var7) {
                        ;
                    }
                }
            }

            machinePiece = sb.toString().hashCode();
        } catch (Throwable var8) {
            machinePiece = (new SecureRandom()).nextInt();
            log.warn("Failed to get machine identifier from network interface, using random number instead", var8);
        }

        machinePiece &= 16777215;
        return machinePiece;
    }

    public static short createProcessIdentifier() {
        short processId;
        try {
            String processName = ManagementFactory.getRuntimeMXBean().getName();
            if (processName.contains("@")) {
                processId = (short)Integer.parseInt(processName.substring(0, processName.indexOf(64)));
            } else {
                processId = (short)ManagementFactory.getRuntimeMXBean().getName().hashCode();
            }
        } catch (Throwable var2) {
            processId = (short)(new SecureRandom()).nextInt();
            log.warn("Failed to get process identifier from JMX, using random number instead", var2);
        }

        return processId;
    }
}
