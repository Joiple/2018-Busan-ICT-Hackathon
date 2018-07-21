package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class NfcService extends HostApduService {
    private  final String SAMPLE_LOYALTY_CARD_AID = "F222222222";
    private  final String SELECT_APDU_HEADER = "00A40400";
    private  final byte[] SELECT_APDU = BuildSelectApdu(SAMPLE_LOYALTY_CARD_AID);
    private  final String NFC = "NFC";
    private  final byte[] SELECT_OK_SW = HexStringToByteArray("9000");
    private  final byte[] UNKNOWN_CMD_SW = HexStringToByteArray("0000");

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();
        return START_STICKY;
    }

    @Override
    public byte[] processCommandApdu(byte[] bytes, Bundle bundle) {
        if (Arrays.equals(SELECT_APDU, bytes)) {
            Log.i(NFC, "nfc connected");
            //TODO return value is sending value;
            return SELECT_OK_SW;
        } else {
            return UNKNOWN_CMD_SW;
        }
    }

    public byte[] BuildSelectApdu(String aid) {
        // Format: [CLASS | INSTRUCTION | PARAMETER 1 | PARAMETER 2 | LENGTH | DATA]
        return HexStringToByteArray(SELECT_APDU_HEADER + String.format("%02X", aid.length() / 2) + aid);
    }

    public static byte[] HexStringToByteArray(String s) throws IllegalArgumentException {
        int len = s.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        byte[] data = new byte[len / 2];
        // Allocate 1 byte per 2 hex characters
        for (int i = 0; i < len; i += 2) {
            // Convert each character into a integer (base-16), then bit-shift into place
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    @Override
    public void onDeactivated(int i) {
        Log.i(NFC,"Deactivated"+i);
    }
}
