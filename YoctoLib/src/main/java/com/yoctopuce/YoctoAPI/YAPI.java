/*********************************************************************
 * $Id: YAPI.java 46942 2021-10-28 08:51:53Z seb $
 *
 * High-level programming interface, common to all modules
 *
 * - - - - - - - - - License information: - - - - - - - - -
 *
 * Copyright (C) 2011 and beyond by Yoctopuce Sarl, Switzerland.
 *
 * Yoctopuce Sarl (hereafter Licensor) grants to you a perpetual
 * non-exclusive license to use, modify, copy and integrate this
 * file into your software for the sole purpose of interfacing
 * with Yoctopuce products.
 *
 * You may reproduce and distribute copies of this file in
 * source or object form, as long as the sole purpose of this
 * code is to interface with Yoctopuce products. You must retain
 * this notice in the distributed source file.
 *
 * You should refer to Yoctopuce General Terms and Conditions
 * for additional information regarding your rights and
 * obligations.
 *
 * THE SOFTWARE AND DOCUMENTATION ARE PROVIDED 'AS IS' WITHOUT
 * WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING
 * WITHOUT LIMITATION, ANY WARRANTY OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO
 * EVENT SHALL LICENSOR BE LIABLE FOR ANY INCIDENTAL, SPECIAL,
 * INDIRECT OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA,
 * COST OF PROCUREMENT OF SUBSTITUTE GOODS, TECHNOLOGY OR
 * SERVICES, ANY CLAIMS BY THIRD PARTIES (INCLUDING BUT NOT
 * LIMITED TO ANY DEFENSE THEREOF), ANY CLAIMS FOR INDEMNITY OR
 * CONTRIBUTION, OR OTHER SIMILAR COSTS, WHETHER ASSERTED ON THE
 * BASIS OF CONTRACT, TORT (INCLUDING NEGLIGENCE), BREACH OF
 * WARRANTY, OR OTHERWISE.
 *********************************************************************/

package com.yoctopuce.YoctoAPI;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
@SuppressWarnings("unused")
public class YAPI
{

    // Return value for invalid strings
    public static final String INVALID_STRING = "!INVALID!";
    public static final double INVALID_DOUBLE = -1.79769313486231E+308;
    static final double MAX_DOUBLE = Double.MAX_VALUE;
    static final double MIN_DOUBLE = Double.MIN_VALUE;
    public static final int INVALID_INT = -2147483648;
    public static final long INVALID_LONG = -9223372036854775807L;
    public static final int INVALID_UINT = -1;
    public static final String YOCTO_API_VERSION_STR = "1.10";
    public static final String YOCTO_API_BUILD_STR = "49822";
    public static final int YOCTO_API_VERSION_BCD = 0x0110;
    public static final int YOCTO_VENDORID = 0x24e0;
    public static final int YOCTO_DEVID_FACTORYBOOT = 1;
    public static final int YOCTO_DEVID_BOOTLOADER = 2;
    static final int HASH_BUF_SIZE = 28;

    public static final int YOCTO_DEFAULT_PORT = 4444;
    public static final int YOCTO_DEFAULT_HTTPS_PORT = 4443;

    // --- (generated code: YFunction return codes)
    // Yoctopuce error codes, used by default as function return value
    public static final int SUCCESS = 0;                   // everything worked all right
    public static final int NOT_INITIALIZED = -1;          // call yInitAPI() first !
    public static final int INVALID_ARGUMENT = -2;         // one of the arguments passed to the function is invalid
    public static final int NOT_SUPPORTED = -3;            // the operation attempted is (currently) not supported
    public static final int DEVICE_NOT_FOUND = -4;         // the requested device is not reachable
    public static final int VERSION_MISMATCH = -5;         // the device firmware is incompatible with this API version
    public static final int DEVICE_BUSY = -6;              // the device is busy with another task and cannot answer
    public static final int TIMEOUT = -7;                  // the device took too long to provide an answer
    public static final int IO_ERROR = -8;                 // there was an I/O problem while talking to the device
    public static final int NO_MORE_DATA = -9;             // there is no more data to read from
    public static final int EXHAUSTED = -10;               // you have run out of a limited resource, check the documentation
    public static final int DOUBLE_ACCES = -11;            // you have two process that try to access to the same device
    public static final int UNAUTHORIZED = -12;            // unauthorized access to password-protected device
    public static final int RTC_NOT_READY = -13;           // real-time clock has not been initialized (or time was lost)
    public static final int FILE_NOT_FOUND = -14;          // the file is not found
    public static final int SSL_ERROR = -15;               // Error reported by mbedSSL

//--- (end of generated code: YFunction return codes)
    static final String DefaultEncoding = "ISO-8859-1";

    // Encoding types
    static final int YOCTO_CALIB_TYPE_OFS = 30;

    // Yoctopuce generic constant
    public static final int YOCTO_MANUFACTURER_LEN = 20;
    public static final int YOCTO_SERIAL_LEN = 20;
    public static final int YOCTO_BASE_SERIAL_LEN = 8;
    public static final int YOCTO_PRODUCTNAME_LEN = 28;
    public static final int YOCTO_FIRMWARE_LEN = 22;
    public static final int YOCTO_LOGICAL_LEN = 20;
    public static final int YOCTO_FUNCTION_LEN = 20;
    static final int YOCTO_PUBVAL_SIZE = 6; // Size of the data (can be non null
    static final int YOCTO_PUBVAL_LEN = 16; // Temporary storage, >=
    static final int YOCTO_PASS_LEN = 20;
    static final int YOCTO_REALM_LEN = 20;

    // yInitAPI argument
    public static final int DETECT_NONE = 0;
    public static final int DETECT_USB = 1;
    public static final int DETECT_NET = 2;
    public static final int RESEND_MISSING_PKT = 4;
    public static final int DETECT_ALL = DETECT_USB | DETECT_NET;


    /**
     *
     */
    public interface DeviceArrivalCallback
    {

        void yDeviceArrival(YModule module);
    }

    public interface DeviceRemovalCallback
    {

        void yDeviceRemoval(YModule module);
    }

    public interface DeviceChangeCallback
    {

        void yDeviceChange(YModule module);
    }

    public interface LogCallback
    {

        void yLog(String line);
    }

    public interface CalibrationHandlerCallback
    {

        @SuppressWarnings("UnusedParameters")
        double yCalibrationHandler(double rawValue, int calibType,
                                   ArrayList<Integer> params, ArrayList<Double> rawValues, ArrayList<Double> refValues);
    }

    static final HashMap<String, YPEntry.BaseClass> _BaseType;

    static {
        _BaseType = new HashMap<>();
        _BaseType.put("Function", YPEntry.BaseClass.Function);
        _BaseType.put("Sensor", YPEntry.BaseClass.Sensor);
    }


    public interface HubDiscoveryCallback
    {
        /**
         * @param serial : the serial number of the discovered Hub
         * @param url    : the URL (with port number) of the discoveredHub
         */
        void yHubDiscoveryCallback(String serial, String url);
    }


    private static HashMap<Long, YAPIContext> _MultipleYAPI = null;
    private static YAPIContext _SingleYAPI = null;


    @SuppressWarnings("UnusedDeclaration")
    public static synchronized void SetThreadSpecificMode() throws YAPI_Exception
    {
        if (_SingleYAPI != null)
            throw new YAPI_Exception(INVALID_ARGUMENT, "SetSingleThreadMode must be called before start using the Yoctopuce API");
        _MultipleYAPI = new HashMap<>();
    }


    static synchronized YAPIContext GetYCtx(boolean instanciateNew)
    {
        if (_MultipleYAPI != null) {
            YAPIContext context = _MultipleYAPI.get(Thread.currentThread().getId());
            if (context == null && instanciateNew) {
                context = new YAPIContext();
                _MultipleYAPI.put(Thread.currentThread().getId(), context);
            }
            return context;
        } else {
            if (_SingleYAPI == null && instanciateNew) {
                _SingleYAPI = new YAPIContext();
            }
            return _SingleYAPI;
        }
    }


    static String Base64Encode(byte[] data, int ofs, int len)
    {
        int i, j;
        StringBuilder res = new StringBuilder(len * 3 / 2);

        // Get 3 input octets and split them into 4 output hextets (6-bits each)
        while (len > 0) {
            byte[] tmp_buff = new byte[4];
            // Start out treating the output as all padding
            tmp_buff[0] = (byte) 0xFF;
            tmp_buff[1] = (byte) 0xFF;
            tmp_buff[2] = (byte) 0xFF;
            tmp_buff[3] = (byte) 0xFF;

            i = data[ofs++] & 0xff;
            len--;
            tmp_buff[0] = (byte) ((i & 0xFC) >> 2);
            tmp_buff[1] = (byte) ((i & 0x03) << 4);
            if (len > 0) {
                i = data[ofs++];
                len--;
                tmp_buff[1] |= (i & 0xF0) >> 4;
                tmp_buff[2] = (byte) ((i & 0x0F) << 2);
                if (len > 0) {
                    i = data[ofs++];
                    len--;
                    tmp_buff[2] |= (i & 0xC0) >> 6;
                    tmp_buff[3] = (byte) (i & 0x3F);
                }
            }

            // Convert hextets into Base 64 alphabet and store result
            for (i = 0; i < 4; i++) {
                j = tmp_buff[i] & 0xff;
                if (j <= 25)
                    res.append((char) ('A' + j));
                else if (j <= 51)
                    res.append((char) ('a' + j - 26));
                else if (j <= 61)
                    res.append((char) ('0' + j - 52));
                else if (j == 62)
                    res.append('+');
                else if (j == 63)
                    res.append('/');
                else                // Padding
                    res.append('=');
            }
        }
        return res.toString();
    }
    //PUBLIC STATIC METHOD:


    /**
     * Enables the acknowledge of every USB packet received by the Yoctopuce library.
     * This function allows the library to run on Android phones that tend to loose USB packets.
     * By default, this feature is disabled because it doubles the number of packets sent and slows
     * down the API considerably. Therefore, the acknowledge of incoming USB packets should only be
     * enabled on phones or tablets that loose USB packets. A delay of 50 milliseconds is generally
     * enough. In case of doubt, contact Yoctopuce support. To disable USB packets acknowledge,
     * call this function with the value 0. Note: this feature is only available on Android.
     *
     * @param pktAckDelay : then number of milliseconds before the module
     *         resend the last USB packet.
     */
    public static void SetUSBPacketAckMs(int pktAckDelay)
    {
        YAPIContext yctx = GetYCtx(true);
        yctx.SetUSBPacketAckMs(pktAckDelay);
    }


    /**
     * Returns the version identifier for the Yoctopuce library in use.
     * The version is a string in the form "Major.Minor.Build",
     * for instance "1.01.5535". For languages using an external
     * DLL (for instance C#, VisualBasic or Delphi), the character string
     * includes as well the DLL version, for instance
     * "1.01.5535 (1.01.5439)".
     *
     * If you want to verify in your code that the library version is
     * compatible with the version that you have used during development,
     * verify that the major number is strictly equal and that the minor
     * number is greater or equal. The build number is not relevant
     * with respect to the library compatibility.
     *
     * @return a character string describing the library version.
     */
    public static String GetAPIVersion()
    {
        return YOCTO_API_VERSION_STR + ".49822" + YUSBHub.getAPIVersion();
    }

    /**
     * Initializes the Yoctopuce programming library explicitly.
     * It is not strictly needed to call yInitAPI(), as the library is
     * automatically  initialized when calling yRegisterHub() for the
     * first time.
     *
     * When YAPI.DETECT_NONE is used as detection mode,
     * you must explicitly use yRegisterHub() to point the API to the
     * VirtualHub on which your devices are connected before trying to access them.
     *
     * @param mode : an integer corresponding to the type of automatic
     *         device detection to use. Possible values are
     *         YAPI.DETECT_NONE, YAPI.DETECT_USB, YAPI.DETECT_NET,
     *         and YAPI.DETECT_ALL.
     *
     * @return YAPI.SUCCESS when the call succeeds.
     *
     * @throws YAPI_Exception on error
     */
    public static int InitAPI(int mode) throws YAPI_Exception
    {
        YAPIContext yctx = GetYCtx(true);
        return yctx.InitAPI(mode);
    }

    /**
     * Waits for all pending communications with Yoctopuce devices to be
     * completed then frees dynamically allocated resources used by
     * the Yoctopuce library.
     *
     * From an operating system standpoint, it is generally not required to call
     * this function since the OS will automatically free allocated resources
     * once your program is completed. However there are two situations when
     * you may really want to use that function:
     *
     * - Free all dynamically allocated memory blocks in order to
     * track a memory leak.
     *
     * - Send commands to devices right before the end
     * of the program. Since commands are sent in an asynchronous way
     * the program could exit before all commands are effectively sent.
     *
     * You should not call any other library function after calling
     * yFreeAPI(), or your program will crash.
     */
    public static void FreeAPI()
    {
        YAPIContext yctx;
        if (_MultipleYAPI != null) {
            yctx = _MultipleYAPI.get(Thread.currentThread().getId());
            if (yctx != null) {
                yctx.FreeAPI();
            }
            _MultipleYAPI.remove(Thread.currentThread().getId());
        } else {
            yctx = _SingleYAPI;
            if (yctx != null) {
                yctx.FreeAPI();
            }
            _SingleYAPI = null;
        }

    }


    /**
     * Setup the Yoctopuce library to use modules connected on a given machine. Idealy this
     * call will be made once at the begining of your application.  The
     * parameter will determine how the API will work. Use the following values:
     *
     * <b>usb</b>: When the usb keyword is used, the API will work with
     * devices connected directly to the USB bus. Some programming languages such a JavaScript,
     * PHP, and Java don't provide direct access to USB hardware, so usb will
     * not work with these. In this case, use a VirtualHub or a networked YoctoHub (see below).
     *
     * <b><i>x.x.x.x</i></b> or <b><i>hostname</i></b>: The API will use the devices connected to the
     * host with the given IP address or hostname. That host can be a regular computer
     * running a VirtualHub, or a networked YoctoHub such as YoctoHub-Ethernet or
     * YoctoHub-Wireless. If you want to use the VirtualHub running on you local
     * computer, use the IP address 127.0.0.1.
     *
     * <b>callback</b>: that keyword make the API run in "<i>HTTP Callback</i>" mode.
     * This a special mode allowing to take control of Yoctopuce devices
     * through a NAT filter when using a VirtualHub or a networked YoctoHub. You only
     * need to configure your hub to call your server script on a regular basis.
     * This mode is currently available for PHP and Node.JS only.
     *
     * Be aware that only one application can use direct USB access at a
     * given time on a machine. Multiple access would cause conflicts
     * while trying to access the USB modules. In particular, this means
     * that you must stop the VirtualHub software before starting
     * an application that uses direct USB access. The workaround
     * for this limitation is to setup the library to use the VirtualHub
     * rather than direct USB access.
     *
     * If access control has been activated on the hub, virtual or not, you want to
     * reach, the URL parameter should look like:
     *
     * http://username:password@address:port
     *
     * You can call <i>RegisterHub</i> several times to connect to several machines. On
     * the other hand, it is useless and even counterproductive to call <i>RegisterHub</i>
     * with to same address multiple times during the life of the application.
     *
     * @param url : a string containing either "usb","callback" or the
     *         root URL of the hub to monitor
     *
     * @return YAPI.SUCCESS when the call succeeds.
     *
     * @throws YAPI_Exception on error
     */
    public static int RegisterHub(String url) throws YAPI_Exception
    {
        return GetYCtx(true).RegisterHub(url);
    }


    public static int RegisterHub(String url, InputStream request, OutputStream response) throws YAPI_Exception
    {
        return GetYCtx(true).RegisterHub(url, request, response);
    }

    /**
     *
     */
    public static int PreregisterHubWebSocketCallback(Object session) throws YAPI_Exception
    {
        return GetYCtx(true).PreregisterHubWebSocketCallback(session, null, null);
    }

    /**
     *
     */
    public static int PreregisterHubWebSocketCallback(Object session, String user, String pass) throws YAPI_Exception
    {
        return GetYCtx(true).PreregisterHubWebSocketCallback(session, user, pass);
    }

    /**
     *
     */
    public static void UnregisterHubWebSocketCallback(Object session)
    {
        YAPIContext yCtx = GetYCtx(false);
        if (yCtx == null) {
            return;
        }
        yCtx.UnregisterHubWebSocketCallback(session);
    }

    /**
     * This function is used only on Android. Before calling yRegisterHub("usb")
     * you need to activate the USB host port of the system. This function takes as argument,
     * an object of class android.content.Context (or any subclass).
     * It is not necessary to call this function to reach modules through the network.
     *
     * @param osContext : an object of class android.content.Context (or any subclass).
     *
     * @throws YAPI_Exception on error
     */
    public static void EnableUSBHost(Object osContext) throws YAPI_Exception
    {
        GetYCtx(true).EnableUSBHost(osContext);
    }

    /**
     * Fault-tolerant alternative to yRegisterHub(). This function has the same
     * purpose and same arguments as yRegisterHub(), but does not trigger
     * an error when the selected hub is not available at the time of the function call.
     * This makes it possible to register a network hub independently of the current
     * connectivity, and to try to contact it only when a device is actively needed.
     *
     * @param url : a string containing either "usb","callback" or the
     *         root URL of the hub to monitor
     *
     * @return YAPI.SUCCESS when the call succeeds.
     *
     * @throws YAPI_Exception on error
     */
    public static int PreregisterHub(String url) throws YAPI_Exception
    {
        return GetYCtx(true).PreregisterHub(url);
    }

    /**
     * Setup the Yoctopuce library to no more use modules connected on a previously
     * registered machine with RegisterHub.
     *
     * @param url : a string containing either "usb" or the
     *         root URL of the hub to monitor
     */
    public static void UnregisterHub(String url)
    {
        YAPIContext yCtx = GetYCtx(false);
        if (yCtx == null) {
            return;
        }
        yCtx.UnregisterHub(url);
    }


    /**
     * Test if the hub is reachable. This method do not register the hub, it only test if the
     * hub is usable. The url parameter follow the same convention as the yRegisterHub
     * method. This method is useful to verify the authentication parameters for a hub. It
     * is possible to force this method to return after mstimeout milliseconds.
     *
     * @param url : a string containing either "usb","callback" or the
     *         root URL of the hub to monitor
     * @param mstimeout : the number of millisecond available to test the connection.
     *
     * @return YAPI.SUCCESS when the call succeeds.
     *
     * On failure returns a negative error code.
     */
    public static int TestHub(String url, int mstimeout) throws YAPI_Exception
    {
        return GetYCtx(true).TestHub(url, mstimeout);
    }


    /**
     * Triggers a (re)detection of connected Yoctopuce modules.
     * The library searches the machines or USB ports previously registered using
     * yRegisterHub(), and invokes any user-defined callback function
     * in case a change in the list of connected devices is detected.
     *
     * This function can be called as frequently as desired to refresh the device list
     * and to make the application aware of hot-plug events. However, since device
     * detection is quite a heavy process, UpdateDeviceList shouldn't be called more
     * than once every two seconds.
     *
     * @return YAPI.SUCCESS when the call succeeds.
     *
     * @throws YAPI_Exception on error
     */
    public static int UpdateDeviceList() throws YAPI_Exception
    {

        YAPIContext yCtx = GetYCtx(false);
        if (yCtx == null) {
            throw new YAPI_Exception(NOT_INITIALIZED, "API not initialized");
        }
        return yCtx.UpdateDeviceList();
    }

    /**
     * Maintains the device-to-library communication channel.
     * If your program includes significant loops, you may want to include
     * a call to this function to make sure that the library takes care of
     * the information pushed by the modules on the communication channels.
     * This is not strictly necessary, but it may improve the reactivity
     * of the library for the following commands.
     *
     * This function may signal an error in case there is a communication problem
     * while contacting a module.
     *
     * @return YAPI.SUCCESS when the call succeeds.
     *
     * @throws YAPI_Exception on error
     */
    public static int HandleEvents() throws YAPI_Exception
    {
        YAPIContext yCtx = GetYCtx(false);
        if (yCtx == null) {
            throw new YAPI_Exception(NOT_INITIALIZED, "API not initialized");
        }
        return yCtx.HandleEvents();
    }

    /**
     * Pauses the execution flow for a specified duration.
     * This function implements a passive waiting loop, meaning that it does not
     * consume CPU cycles significantly. The processor is left available for
     * other threads and processes. During the pause, the library nevertheless
     * reads from time to time information from the Yoctopuce modules by
     * calling yHandleEvents(), in order to stay up-to-date.
     *
     * This function may signal an error in case there is a communication problem
     * while contacting a module.
     *
     * @param ms_duration : an integer corresponding to the duration of the pause,
     *         in milliseconds.
     *
     * @return YAPI.SUCCESS when the call succeeds.
     *
     * @throws YAPI_Exception on error
     */
    public static int Sleep(long ms_duration) throws YAPI_Exception
    {
        YAPIContext yCtx = GetYCtx(false);
        if (yCtx == null) {
            throw new YAPI_Exception(NOT_INITIALIZED, "API not initialized");
        }
        return yCtx.Sleep(ms_duration);
    }

    /**
     * Force a hub discovery, if a callback as been registered with yRegisterHubDiscoveryCallback it
     * will be called for each net work hub that will respond to the discovery.
     *
     * @return YAPI.SUCCESS when the call succeeds.
     * @throws YAPI_Exception on error
     */
    public static int TriggerHubDiscovery() throws YAPI_Exception
    {
        YAPIContext yCtx = GetYCtx(false);
        if (yCtx == null) {
            throw new YAPI_Exception(NOT_INITIALIZED, "API not initialized");
        }
        return yCtx.TriggerHubDiscovery();
    }

    /**
     * Returns the current value of a monotone millisecond-based time counter.
     * This counter can be used to compute delays in relation with
     * Yoctopuce devices, which also uses the millisecond as timebase.
     *
     * @return a long integer corresponding to the millisecond counter.
     */
    public static long GetTickCount()
    {
        return System.currentTimeMillis();
    }

    /**
     * Checks if a given string is valid as logical name for a module or a function.
     * A valid logical name has a maximum of 19 characters, all among
     * A..Z, a..z, 0..9, _, and -.
     * If you try to configure a logical name with an incorrect string,
     * the invalid characters are ignored.
     *
     * @param name : a string containing the name to check.
     *
     * @return true if the name is valid, false otherwise.
     */
    public static boolean CheckLogicalName(String name)
    {
        return name != null && (name.equals("") || name.length() <= 19 && name.matches("^[A-Za-z0-9_-]*$"));
    }

    /**
     * Register a callback function, to be called each time
     * a device is plugged. This callback will be invoked while yUpdateDeviceList
     * is running. You will have to call this function on a regular basis.
     *
     * @param arrivalCallback : a procedure taking a YModule parameter, or null
     *         to unregister a previously registered  callback.
     */
    public static void RegisterDeviceArrivalCallback(YAPI.DeviceArrivalCallback arrivalCallback)
    {
        GetYCtx(true).RegisterDeviceArrivalCallback(arrivalCallback);
    }

    public static void RegisterDeviceChangeCallback(YAPI.DeviceChangeCallback changeCallback)
    {
        GetYCtx(true).RegisterDeviceChangeCallback(changeCallback);
    }

    /**
     * Register a callback function, to be called each time
     * a device is unplugged. This callback will be invoked while yUpdateDeviceList
     * is running. You will have to call this function on a regular basis.
     *
     * @param removalCallback : a procedure taking a YModule parameter, or null
     *         to unregister a previously registered  callback.
     */
    public static void RegisterDeviceRemovalCallback(YAPI.DeviceRemovalCallback removalCallback)
    {
        GetYCtx(true).RegisterDeviceRemovalCallback(removalCallback);
    }

    /**
     * Register a callback function, to be called each time an Network Hub send
     * an SSDP message. The callback has two string parameter, the first one
     * contain the serial number of the hub and the second contain the URL of the
     * network hub (this URL can be passed to RegisterHub). This callback will be invoked
     * while yUpdateDeviceList is running. You will have to call this function on a regular basis.
     *
     * @param hubDiscoveryCallback : a procedure taking two string parameter, the serial
     *         number and the hub URL. Use null to unregister a previously registered  callback.
     */
    public static void RegisterHubDiscoveryCallback(YAPI.HubDiscoveryCallback hubDiscoveryCallback)
    {
        YAPIContext yCtx = GetYCtx(false);
        if (yCtx == null) {
            return;
        }
        yCtx.RegisterHubDiscoveryCallback(hubDiscoveryCallback);
    }

    /**
     * Registers a log callback function. This callback will be called each time
     * the API have something to say. Quite useful to debug the API.
     *
     * @param logfun : a procedure taking a string parameter, or null
     *         to unregister a previously registered  callback.
     */
    public static void RegisterLogFunction(YAPI.LogCallback logfun)
    {
        GetYCtx(true).RegisterLogFunction(logfun);
    }


//--- (generated code: YAPIContext yapiwrapper)
    /**
     * Modifies the delay between each forced enumeration of the used YoctoHubs.
     * By default, the library performs a full enumeration every 10 seconds.
     * To reduce network traffic, you can increase this delay.
     * It's particularly useful when a YoctoHub is connected to the GSM network
     * where traffic is billed. This parameter doesn't impact modules connected by USB,
     * nor the working of module arrival/removal callbacks.
     * Note: you must call this function after yInitAPI.
     *
     * @param deviceListValidity : nubmer of seconds between each enumeration.
     *
     */
    public static void SetDeviceListValidity(int deviceListValidity)
    {
        GetYCtx(true).SetDeviceListValidity(deviceListValidity);
    }
    /**
     * Returns the delay between each forced enumeration of the used YoctoHubs.
     * Note: you must call this function after yInitAPI.
     *
     * @return the number of seconds between each enumeration.
     */
    public static int GetDeviceListValidity()
    {
        return GetYCtx(true).GetDeviceListValidity();
    }
    /**
     * Adds a UDEV rule which authorizes all users to access Yoctopuce modules
     * connected to the USB ports. This function works only under Linux. The process that
     * calls this method must have root privileges because this method changes the Linux configuration.
     *
     * @param force : if true, overwrites any existing rule.
     *
     * @return an empty string if the rule has been added.
     *
     * On failure, returns a string that starts with "error:".
     */
    public static String AddUdevRule(boolean force)
    {
        return GetYCtx(true).AddUdevRule(force);
    }
    /**
     * Modifies the network connection delay for yRegisterHub() and yUpdateDeviceList().
     * This delay impacts only the YoctoHubs and VirtualHub
     * which are accessible through the network. By default, this delay is of 20000 milliseconds,
     * but depending or you network you may want to change this delay,
     * gor example if your network infrastructure is based on a GSM connection.
     *
     * @param networkMsTimeout : the network connection delay in milliseconds.
     *
     */
    public static void SetNetworkTimeout(int networkMsTimeout)
    {
        GetYCtx(true).SetNetworkTimeout(networkMsTimeout);
    }
    /**
     * Returns the network connection delay for yRegisterHub() and yUpdateDeviceList().
     * This delay impacts only the YoctoHubs and VirtualHub
     * which are accessible through the network. By default, this delay is of 20000 milliseconds,
     * but depending or you network you may want to change this delay,
     * for example if your network infrastructure is based on a GSM connection.
     *
     * @return the network connection delay in milliseconds.
     */
    public static int GetNetworkTimeout()
    {
        return GetYCtx(true).GetNetworkTimeout();
    }
    /**
     * Change the validity period of the data loaded by the library.
     * By default, when accessing a module, all the attributes of the
     * module functions are automatically kept in cache for the standard
     * duration (5 ms). This method can be used to change this standard duration,
     * for example in order to reduce network or USB traffic. This parameter
     * does not affect value change callbacks
     * Note: This function must be called after yInitAPI.
     *
     * @param cacheValidityMs : an integer corresponding to the validity attributed to the
     *         loaded function parameters, in milliseconds.
     *
     */
    public static void SetCacheValidity(long cacheValidityMs)
    {
        GetYCtx(true).SetCacheValidity(cacheValidityMs);
    }
    /**
     * Returns the validity period of the data loaded by the library.
     * This method returns the cache validity of all attributes
     * module functions.
     * Note: This function must be called after yInitAPI .
     *
     * @return an integer corresponding to the validity attributed to the
     *         loaded function parameters, in milliseconds
     */
    public static long GetCacheValidity()
    {
        return GetYCtx(true).GetCacheValidity();
    }
//--- (end of generated code: YAPIContext yapiwrapper)

}