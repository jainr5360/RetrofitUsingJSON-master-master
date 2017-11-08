package com.hogo.rahul.retrofitusingjson.Retrofit2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Utils {

    // Preference files
    public static final String USER_PREFERENCE = "UserPreferences";
    public static final String TAG = "fragment";
    public static final String SETTING_PREFERENCE = "SettingPreferences";
    private static final String DISPLAY_MESSAGE_ACTION = "paramount.healthsmart.utils.DISPLAY_MESSAGE";

    public static final String WEB_API_BASE_URL = "http://biryanihouseuganda.com/";
    public static final String WEB_API_BASE_URL_FOR_DOC = "http://123.252.193.211:90/Web-Api/";

    public static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        context.sendBroadcast(intent);
    }

    public static void saveUserPreference(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public static void saveUserPreference(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void saveUserPreference(Context context, String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static String getStringUserPreference(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }


    /**
     * Get boolean content from User Shared Preference.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name as {@link Boolean}
     * @return Value as {@link String}
     */
    public static boolean getBooleanUserPreference(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }

    /**
     * Get int content from User Shared Preference.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name as {@link Integer}
     * @return Value as {@link String}
     */
    public static int getIntegerUserPreference1(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, -1);
    }

    /**
     * Check if preference already exists.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name to check existence
     * @return True if it exists else false.
     */
    public static boolean isUserPreferenceExists(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.contains(key);
    }

    /**
     * Remove a content from any Shared Preference by it's key and file name.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name as {@link String}
     * @return True if successfully deleted the preference else False.
     */
    public static boolean removePreference(Context context, String preference, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return sharedPref.edit().remove(key).commit();
    }

    /**
     * Delete all Preferences.
     *
     * @param context {@link Activity} {@link Context}
     * @return True if successfully deleted all preferences else False.
     */
    public static boolean clearAllPreference(Context context) {
        SharedPreferences sharedUserPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences sharedSettingPref = context.getSharedPreferences(SETTING_PREFERENCE, Context.MODE_PRIVATE);
        return sharedUserPref.edit().clear().commit() && sharedSettingPref.edit().clear().commit();
    }

    /**
     * Retrofit client to call web service.
     */
    public static WebService getWebService() {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.readTimeout(60, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS);

        return new Retrofit.Builder()                                   // Retrofit client.
                .baseUrl(WEB_API_BASE_URL)                                       // Base domain URL.
                .addConverterFactory(GsonConverterFactory.create())     // Added converter factory.
                .client(okHttpClient.build())
                .build()                                                // Build client.
                .create(WebService.class);
    }

 /*   public static WebService getWebService(Context context) {

        return new Retrofit.Builder()
                .baseUrl(WEB_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(SelfSigningClientBuilder.createClient(context))
                .build().create(WebService.class);
    }*/


    public static WebService getWebServiceForAuthorization() {
        //     Log.e(TAG, "Authorization" + " Bearer " + SPreferences.SSP().getString(Constants.AECCESS_TOKEN));

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.readTimeout(60, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request().newBuilder()
                        //              .addHeader("Authorization", " Bearer " + SPreferences.SSP().getString(Constants.AECCESS_TOKEN))
                        .build();

                return chain.proceed(request);
            }
        });


        return new Retrofit.Builder()                                   // Retrofit client.
                .baseUrl(WEB_API_BASE_URL)                                // Base domain URL.
                .addConverterFactory(GsonConverterFactory.create())     // Added converter factory.
                .client(okHttpClient.build())
                .build()                                                // Build client.
                .create(WebService.class);
    }


    public static WebService getWebServiceForLogin() {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS);
        okHttpClient.readTimeout(20, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(20, TimeUnit.SECONDS);

        return new Retrofit.Builder()                                   // Retrofit client.
                .baseUrl(WEB_API_BASE_URL)                                // Base domain URL.
                .addConverterFactory(GsonConverterFactory.create())     // Added converter factory.
                .client(okHttpClient.build())
                .build()                                                // Build client.
                .create(WebService.class);
    }

    /**
     * Check user internet connectivity.
     *
     * @param context Activity context
     * @return Connected to internet or not. (True / False)
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    /**
     * A Toast message if device is not connected to internet.
     *
     * @param context Activity context.
     */
    public static void offlineMessage(Context context) {
        //Toast.makeText(context, context.getResources().getString(R.string.device_offline_message), Toast.LENGTH_LONG).show();
    }

    /**
     * Pass 29-04-2016 Time and getFormat
     *
     * @param datetime
     * @return String date
     */
    @SuppressLint("SimpleDateFormat")
    public static String dateFormat(String datetime) {
        if (datetime != null)
            try { // Expiry date
                String s;
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(datetime);
                sdf.setTimeZone(TimeZone.getTimeZone("IST"));
                s = sdf.format(date).split("-")[2] +
                        "-" + sdf.format(date).split("-")[1] +
                        "-" + sdf.format(date).split("-")[0];
                return s;
            } catch (Exception e) {
                Log.e("AppUtilDateFormat", "dateFormat: Parsing exception ", e);
            }
        return null;
    }

    /**
     * Convert Time from 24 hours to 12 hours.
     *
     * @param datetime
     * @return
     */
    public static String timeFormat(String datetime) {
        SimpleDateFormat inTimeFormat = new SimpleDateFormat("HH:mm", Locale.US);
        SimpleDateFormat outTimeFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        try { // Expiry date
            Date eDate = inTimeFormat.parse(datetime.split(" ")[1]);
            return outTimeFormat.format(eDate);
        } catch (ParseException e) {
            Log.e("AppUtilDateFormat", "timeFormat: Parsing exception ", e);
        }
        return null;
    }


    public static String numberFormat(Float number) {
        NumberFormat formatter;
        // Expiry date
        formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter.format(number);

    }

    public static boolean isValidMobile(String phone) {
        if (phone.length() != 10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }
    }

    public static boolean isValidMobileNumber(CharSequence target) {

        boolean flg1 = false, flg2 = false;
        String MobilePattern = "^[0-9]{8}$";

        Pattern pattern = Pattern.compile(MobilePattern);
        Matcher matcher = pattern.matcher(target);
        flg1 = matcher.matches();
        //
        String MobilePattern2 = "^[0-9]{10}$";

        Pattern pattern2 = Pattern.compile(MobilePattern2);
        Matcher matcher2 = pattern2.matcher(target);
        flg2 = matcher2.matches();
        if (flg1 || flg2)
            return true;
        else
            return false;

    }

    public static boolean checkChar(String target) {

        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {

        return Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,20})").matcher(password).matches();
    }

    // check play services
 /*   public static boolean checkPlayServices(Context context) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog((Activity) context, resultCode, 9000)
                        .show();
            } else {
                Log.e("failed", "checkPlayServices: ");
            }
            return false;
        }
        return true;
    }*/

    // Check internet is available or not..


    public static Toast customMessage(Context ctx, String message) {

        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return toast;

    }



   /* public static boolean dataNotFound(final Activity activity, final boolean close, final FragmentManager fragmentManager) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.page_not_found_layout, null);
        builder.setView(dialogLayout)
                // builder.setMessage("Sorry No Records Found..")
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("NO RECORD FOUND!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (close) {
                            fragmentManager.popBackStack();
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        return true;
    }*/


    public static String getDayName(int pos) {
        String[] daysName = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return daysName[pos];
    }

    public static int getDayInInteger(String s) {
        switch (s) {
            case "Sunday":
                return 0;
            case "Monday":
                return 1;
            case "Tuesday":
                return 2;
            case "Wednesday":
                return 3;
            case "Thursday":
                return 4;
            case "Friday":
                return 5;
            case "Saturday":
                return 6;
            default:
                return 0;
        }
    }


    public static String getMonthName(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April ";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        return null;
    }

    public static String fullDay(int month) {
        switch (month) {
            case 1:
                return "01";
            case 2:
                return "02";
            case 3:
                return "03";
            case 4:
                return "04";
            case 5:
                return "05";
            case 6:
                return "06";
            case 7:
                return "07";
            case 8:
                return "08";
            case 9:
                return "09";
            default:
                return "" + month;

        }
    }

    public static int getMonthPos(String month) {
        switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April ":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
        }
        return 0;
    }

    public static String getMonthNameFromMM(String month) {
        switch (month) {
            case "Jan":
                return "January";
            case "Feb":
                return "February";
            case "Mar":
                return "March";
            case "Apr":
                return "April ";
            case "May":
                return "May";
            case "Jun":
                return "June";
            case "Jul":
                return "July";
            case "Aug":
                return "August";
            case "Sep":
                return "September";
            case "Oct":
                return "October";
            case "Nov":
                return "November";
            case "Dec":
                return "December";
        }
        return null;
    }

   /* public static boolean dataNotFound(final Activity activity, final boolean close) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.page_not_found_layout, null);
        builder.setView(dialogLayout)
                // builder.setMessage("Sorry No Records Found..")
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("NO RECORD FOUND!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (close) {
                            activity.finish();
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        return true;
    }*/

    /*convtoers*/
    private static double format(double value) {
        if (value != 0) {
            DecimalFormat df = new DecimalFormat("###.####");
            return Double.valueOf(df.format(value));
        } else {
            return -1;
        }
    }

    public static double lbToKgConverter(double lb) {
        Log.e(TAG, "pound =>kg " + format(lb * 0.45359237));
        return format(lb * 0.45359237);
    }

    public static double kgToLbConverter(double kg) {
        Log.e(TAG, "kg => pound" + format(kg * 2.20462262));
        return format(kg * 2.20462262);
    }

    public String convertCentimeterToHeight(double d) {
        int feetPart = 0;
        int inchesPart = 0;
        if (String.valueOf(d) != null && String.valueOf(d).trim().length() != 0) {
            feetPart = (int) Math.floor((d / 2.54) / 12);
            //inchesPart = (int) Math.ceil((d / 2.54) - (feetPart * 12));
            inchesPart = (int) Math.floor((d / 2.54) - (feetPart * 12));
        }
        Log.e(TAG, "Centimeter => Height: " + String.format("%d %d", feetPart, inchesPart));
        return String.format("%d %d", feetPart, inchesPart);
    }

    public static double convertFeetandInchesToCentimeter(String feet, String inches) {
        double heightInFeet = 0;
        double heightInInches = 0;
        try {
            if (feet != null && feet.trim().length() != 0) {
                heightInFeet = Double.parseDouble(feet);
            }
            if (inches != null && inches.trim().length() != 0) {
                heightInInches = Double.parseDouble(inches);
            }
            heightInInches += heightInFeet * 12;

        } catch (NumberFormatException nfe) {

        }
        Log.e(TAG, "Feetand Inches => Centimeter : " + (heightInInches * 2.54));
        return (heightInInches * 2.54);
    }



}
