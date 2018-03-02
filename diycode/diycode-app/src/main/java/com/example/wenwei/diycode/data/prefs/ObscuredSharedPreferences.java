package com.example.wenwei.diycode.data.prefs;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.wenwei.utils.AESCrypt;

import java.util.Map;
import java.util.Set;

/**
 * Warning, this gives a false sense of security.  If an attacker has enough access to
 * acquire your password store, then he almost certainly has enough access to acquire your
 * source binary and figure out your encryption key.  However, it will prevent casual
 * investigators from acquiring passwords, and thereby may prevent undesired negative
 * publicity.
 */
public class ObscuredSharedPreferences implements SharedPreferences {
    protected SharedPreferences delegate;
    protected Context context;

    public ObscuredSharedPreferences(Context context, String name, int mode) {
        this.delegate = context.getApplicationContext().getSharedPreferences(name, mode);
        this.context = context;
    }

    public class Editor implements SharedPreferences.Editor {
        protected SharedPreferences.Editor delegate;

        public Editor() {
            SharedPreferences.Editor editor = ObscuredSharedPreferences.this.delegate.edit();
            editor.apply();
            this.delegate = editor;
        }

        @Override
        public Editor putString(String key, @Nullable String value) {
            delegate.putString(key, AESCrypt.encrypt(value));
            return this;
        }

        @Override
        public Editor putStringSet(String key, @Nullable Set<String> values) {
            // TODO: Encrypt StringSet
            delegate.putStringSet(key, values);
            return this;
        }

        @Override
        public Editor putInt(String key, int value) {
            delegate.putString(key, AESCrypt.encrypt(Integer.toString(value)));
            return this;
        }

        @Override
        public Editor putLong(String key, long value) {
            delegate.putString(key, AESCrypt.encrypt(Long.toString(value)));
            return this;
        }

        @Override
        public Editor putFloat(String key, float value) {
            delegate.putString(key, AESCrypt.encrypt(Float.toString(value)));
            return this;
        }

        @Override
        public Editor putBoolean(String key, boolean value) {
            delegate.putString(key, AESCrypt.encrypt(Boolean.toString(value)));
            return this;
        }

        @Override
        public Editor remove(String key) {
            delegate.remove(key);
            return this;
        }

        @Override
        public Editor clear() {
            delegate.clear();
            return this;
        }

        @Override
        public boolean commit() {
            return delegate.commit();
        }

        @Override
        public void apply() {
            delegate.apply();
        }
    }

    @Override
    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException(); // left as an exercise to the reader
    }

    @Nullable
    @Override
    public String getString(String key, @Nullable String defValue) {
        final String v = delegate.getString(key, null);
        return v != null ? AESCrypt.decrypt(v) : defValue;
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        return delegate.getStringSet(key, defValues);
    }

    @Override
    public int getInt(String key, int defValue) {
        final String v = delegate.getString(key, null);
        return v != null ? Integer.parseInt(AESCrypt.decrypt(v)) : defValue;
    }

    @Override
    public long getLong(String key, long defValue) {
        final String v = delegate.getString(key, null);
        return v != null ? Long.parseLong(AESCrypt.decrypt(v)) : defValue;
    }

    @Override
    public float getFloat(String key, float defValue) {
        final String v = delegate.getString(key, null);
        return v != null ? Float.parseFloat(AESCrypt.decrypt(v)) : defValue;
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        final String v = delegate.getString(key, null);
        return v != null ? Boolean.parseBoolean(AESCrypt.decrypt(v)) : defValue;
    }

    @Override
    public boolean contains(String key) {
        return delegate.contains(key);
    }

    @Override
    public Editor edit() {
        return new Editor();
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        delegate.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        delegate.unregisterOnSharedPreferenceChangeListener(listener);
    }


}
