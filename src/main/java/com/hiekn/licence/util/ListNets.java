package com.hiekn.licence.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class ListNets {

    public static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        byte[] mac = netint.getHardwareAddress();
        if (mac != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i],
                        (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println("mac=" + sb.toString());
        }

        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            System.out.printf("InetAddress: %s\n", inetAddress);
            System.out.println("InetAddress ip=" + inetAddress.getHostAddress());
        }
        System.out.printf("\n");
    }

    public static boolean validateMacAddress(String macAddress) throws SocketException {
        boolean returnFlag = false;
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            byte[] mac = netint.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            if (mac != null) {
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i],
                            (i < mac.length - 1) ? "-" : ""));
                }
//                System.out.println("mac=" + sb.toString());
            }
            if (sb.toString().equals(macAddress)) {
                returnFlag = true;
            }
        }
        return returnFlag;

    }

    public static boolean validatorIpAndMacAddress(String ipAddress, String macAddress) throws SocketException {
        boolean returnFlag = false;
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            byte[] mac = netint.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            if (mac != null) {
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
//                System.out.println("mac=" + sb.toString());
            }
            if (sb.toString().equals(macAddress)) {
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                String ip = "";
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    ip = inetAddress.getHostAddress();
//                    System.out.println("InetAddress ip=" + inetAddress.getHostAddress());
                    if (ipAddress.equals(ip)) {
                        returnFlag = true;
                    }
                }
            }
        }
        return returnFlag;
    }
}
