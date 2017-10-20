package com.example.wenwei.criminalintent;


import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private HashMap<UUID, Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Every other one
            mCrimes.put(crime.getId(), crime);
        }
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>(mCrimes.values());
    }

    public Crime getCrime(UUID id) {
        return mCrimes.get(id);
    }

}
