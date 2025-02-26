/*********************************************************************
 *
 * $Id: YWireless.java 48017 2022-01-12 08:17:52Z seb $
 *
 * Implements yFindWireless(), the high-level API for Wireless functions
 *
 * - - - - - - - - - License information: - - - - - - - - -
 *
 *  Copyright (C) 2011 and beyond by Yoctopuce Sarl, Switzerland.
 *
 *  Yoctopuce Sarl (hereafter Licensor) grants to you a perpetual
 *  non-exclusive license to use, modify, copy and integrate this
 *  file into your software for the sole purpose of interfacing
 *  with Yoctopuce products.
 *
 *  You may reproduce and distribute copies of this file in
 *  source or object form, as long as the sole purpose of this
 *  code is to interface with Yoctopuce products. You must retain
 *  this notice in the distributed source file.
 *
 *  You should refer to Yoctopuce General Terms and Conditions
 *  for additional information regarding your rights and
 *  obligations.
 *
 *  THE SOFTWARE AND DOCUMENTATION ARE PROVIDED 'AS IS' WITHOUT
 *  WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING
 *  WITHOUT LIMITATION, ANY WARRANTY OF MERCHANTABILITY, FITNESS
 *  FOR A PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO
 *  EVENT SHALL LICENSOR BE LIABLE FOR ANY INCIDENTAL, SPECIAL,
 *  INDIRECT OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA,
 *  COST OF PROCUREMENT OF SUBSTITUTE GOODS, TECHNOLOGY OR
 *  SERVICES, ANY CLAIMS BY THIRD PARTIES (INCLUDING BUT NOT
 *  LIMITED TO ANY DEFENSE THEREOF), ANY CLAIMS FOR INDEMNITY OR
 *  CONTRIBUTION, OR OTHER SIMILAR COSTS, WHETHER ASSERTED ON THE
 *  BASIS OF CONTRACT, TORT (INCLUDING NEGLIGENCE), BREACH OF
 *  WARRANTY, OR OTHERWISE.
 *
 *********************************************************************/

package com.yoctopuce.YoctoAPI;


import java.util.ArrayList;
import java.util.Locale;

//--- (generated code: YWireless class start)
/**
 *  YWireless Class: wireless LAN interface control interface, available for instance in the
 * YoctoHub-Wireless, the YoctoHub-Wireless-SR, the YoctoHub-Wireless-g or the YoctoHub-Wireless-n
 *
 * The YWireless class provides control over wireless network parameters
 * and status for devices that are wireless-enabled.
 * Note that TCP/IP parameters are configured separately, using class YNetwork.
 */
@SuppressWarnings({"UnusedDeclaration", "UnusedAssignment"})
public class YWireless extends YFunction
{
//--- (end of generated code: YWireless class start)


    //--- (generated code: YWireless definitions)
    /**
     * invalid linkQuality value
     */
    public static final int LINKQUALITY_INVALID = YAPI.INVALID_UINT;
    /**
     * invalid ssid value
     */
    public static final String SSID_INVALID = YAPI.INVALID_STRING;
    /**
     * invalid channel value
     */
    public static final int CHANNEL_INVALID = YAPI.INVALID_UINT;
    /**
     * invalid security value
     */
    public static final int SECURITY_UNKNOWN = 0;
    public static final int SECURITY_OPEN = 1;
    public static final int SECURITY_WEP = 2;
    public static final int SECURITY_WPA = 3;
    public static final int SECURITY_WPA2 = 4;
    public static final int SECURITY_INVALID = -1;
    /**
     * invalid message value
     */
    public static final String MESSAGE_INVALID = YAPI.INVALID_STRING;
    /**
     * invalid wlanConfig value
     */
    public static final String WLANCONFIG_INVALID = YAPI.INVALID_STRING;
    /**
     * invalid wlanState value
     */
    public static final int WLANSTATE_DOWN = 0;
    public static final int WLANSTATE_SCANNING = 1;
    public static final int WLANSTATE_CONNECTED = 2;
    public static final int WLANSTATE_REJECTED = 3;
    public static final int WLANSTATE_INVALID = -1;
    protected int _linkQuality = LINKQUALITY_INVALID;
    protected String _ssid = SSID_INVALID;
    protected int _channel = CHANNEL_INVALID;
    protected int _security = SECURITY_INVALID;
    protected String _message = MESSAGE_INVALID;
    protected String _wlanConfig = WLANCONFIG_INVALID;
    protected int _wlanState = WLANSTATE_INVALID;
    protected UpdateCallback _valueCallbackWireless = null;

    /**
     * Deprecated UpdateCallback for Wireless
     */
    public interface UpdateCallback
    {
        /**
         *
         * @param function      : the function object of which the value has changed
         * @param functionValue : the character string describing the new advertised value
         */
        void yNewValue(YWireless function, String functionValue);
    }

    /**
     * TimedReportCallback for Wireless
     */
    public interface TimedReportCallback
    {
        /**
         *
         * @param function : the function object of which the value has changed
         * @param measure  : measure
         */
        void timedReportCallback(YWireless  function, YMeasure measure);
    }
    //--- (end of generated code: YWireless definitions)

    /**
     * @param func : functionid
     */
    protected YWireless(YAPIContext yctx, String func)
    {
        super(yctx, func);
        _className = "Wireless";
        //--- (generated code: YWireless attributes initialization)
        //--- (end of generated code: YWireless attributes initialization)
    }

    protected YWireless(String func)
    {
        this(YAPI.GetYCtx(true), func);
    }


    //--- (generated code: YWireless implementation)
    @SuppressWarnings("EmptyMethod")
    @Override
    protected void  _parseAttr(YJSONObject json_val) throws Exception
    {
        if (json_val.has("linkQuality")) {
            _linkQuality = json_val.getInt("linkQuality");
        }
        if (json_val.has("ssid")) {
            _ssid = json_val.getString("ssid");
        }
        if (json_val.has("channel")) {
            _channel = json_val.getInt("channel");
        }
        if (json_val.has("security")) {
            _security = json_val.getInt("security");
        }
        if (json_val.has("message")) {
            _message = json_val.getString("message");
        }
        if (json_val.has("wlanConfig")) {
            _wlanConfig = json_val.getString("wlanConfig");
        }
        if (json_val.has("wlanState")) {
            _wlanState = json_val.getInt("wlanState");
        }
        super._parseAttr(json_val);
    }

    /**
     * Returns the link quality, expressed in percent.
     *
     * @return an integer corresponding to the link quality, expressed in percent
     *
     * @throws YAPI_Exception on error
     */
    public int get_linkQuality() throws YAPI_Exception
    {
        int res;
        synchronized (this) {
            if (_cacheExpiration <= YAPIContext.GetTickCount()) {
                if (load(_yapi._defaultCacheValidity) != YAPI.SUCCESS) {
                    return LINKQUALITY_INVALID;
                }
            }
            res = _linkQuality;
        }
        return res;
    }

    /**
     * Returns the link quality, expressed in percent.
     *
     * @return an integer corresponding to the link quality, expressed in percent
     *
     * @throws YAPI_Exception on error
     */
    public int getLinkQuality() throws YAPI_Exception
    {
        return get_linkQuality();
    }

    /**
     * Returns the wireless network name (SSID).
     *
     * @return a string corresponding to the wireless network name (SSID)
     *
     * @throws YAPI_Exception on error
     */
    public String get_ssid() throws YAPI_Exception
    {
        String res;
        synchronized (this) {
            if (_cacheExpiration <= YAPIContext.GetTickCount()) {
                if (load(_yapi._defaultCacheValidity) != YAPI.SUCCESS) {
                    return SSID_INVALID;
                }
            }
            res = _ssid;
        }
        return res;
    }

    /**
     * Returns the wireless network name (SSID).
     *
     * @return a string corresponding to the wireless network name (SSID)
     *
     * @throws YAPI_Exception on error
     */
    public String getSsid() throws YAPI_Exception
    {
        return get_ssid();
    }

    /**
     * Returns the 802.11 channel currently used, or 0 when the selected network has not been found.
     *
     *  @return an integer corresponding to the 802.11 channel currently used, or 0 when the selected
     * network has not been found
     *
     * @throws YAPI_Exception on error
     */
    public int get_channel() throws YAPI_Exception
    {
        int res;
        synchronized (this) {
            if (_cacheExpiration <= YAPIContext.GetTickCount()) {
                if (load(_yapi._defaultCacheValidity) != YAPI.SUCCESS) {
                    return CHANNEL_INVALID;
                }
            }
            res = _channel;
        }
        return res;
    }

    /**
     * Returns the 802.11 channel currently used, or 0 when the selected network has not been found.
     *
     *  @return an integer corresponding to the 802.11 channel currently used, or 0 when the selected
     * network has not been found
     *
     * @throws YAPI_Exception on error
     */
    public int getChannel() throws YAPI_Exception
    {
        return get_channel();
    }

    /**
     * Returns the security algorithm used by the selected wireless network.
     *
     *  @return a value among YWireless.SECURITY_UNKNOWN, YWireless.SECURITY_OPEN, YWireless.SECURITY_WEP,
     *  YWireless.SECURITY_WPA and YWireless.SECURITY_WPA2 corresponding to the security algorithm used by
     * the selected wireless network
     *
     * @throws YAPI_Exception on error
     */
    public int get_security() throws YAPI_Exception
    {
        int res;
        synchronized (this) {
            if (_cacheExpiration <= YAPIContext.GetTickCount()) {
                if (load(_yapi._defaultCacheValidity) != YAPI.SUCCESS) {
                    return SECURITY_INVALID;
                }
            }
            res = _security;
        }
        return res;
    }

    /**
     * Returns the security algorithm used by the selected wireless network.
     *
     *  @return a value among YWireless.SECURITY_UNKNOWN, YWireless.SECURITY_OPEN, YWireless.SECURITY_WEP,
     *  YWireless.SECURITY_WPA and YWireless.SECURITY_WPA2 corresponding to the security algorithm used by
     * the selected wireless network
     *
     * @throws YAPI_Exception on error
     */
    public int getSecurity() throws YAPI_Exception
    {
        return get_security();
    }

    /**
     * Returns the latest status message from the wireless interface.
     *
     * @return a string corresponding to the latest status message from the wireless interface
     *
     * @throws YAPI_Exception on error
     */
    public String get_message() throws YAPI_Exception
    {
        String res;
        synchronized (this) {
            if (_cacheExpiration <= YAPIContext.GetTickCount()) {
                if (load(_yapi._defaultCacheValidity) != YAPI.SUCCESS) {
                    return MESSAGE_INVALID;
                }
            }
            res = _message;
        }
        return res;
    }

    /**
     * Returns the latest status message from the wireless interface.
     *
     * @return a string corresponding to the latest status message from the wireless interface
     *
     * @throws YAPI_Exception on error
     */
    public String getMessage() throws YAPI_Exception
    {
        return get_message();
    }

    public String get_wlanConfig() throws YAPI_Exception
    {
        String res;
        synchronized (this) {
            if (_cacheExpiration <= YAPIContext.GetTickCount()) {
                if (load(_yapi._defaultCacheValidity) != YAPI.SUCCESS) {
                    return WLANCONFIG_INVALID;
                }
            }
            res = _wlanConfig;
        }
        return res;
    }

    public int set_wlanConfig(String  newval)  throws YAPI_Exception
    {
        String rest_val;
        synchronized (this) {
            rest_val = newval;
            _setAttr("wlanConfig",rest_val);
        }
        return YAPI.SUCCESS;
    }


    /**
     *  Returns the current state of the wireless interface. The state YWireless.WLANSTATE_DOWN means that
     * the network interface is
     *  not connected to a network. The state YWireless.WLANSTATE_SCANNING means that the network interface
     * is scanning available
     *  frequencies. During this stage, the device is not reachable, and the network settings are not yet
     * applied. The state
     *  YWireless.WLANSTATE_CONNECTED means that the network settings have been successfully applied ant
     * that the device is reachable
     *  from the wireless network. If the device is configured to use ad-hoc or Soft AP mode, it means that
     * the wireless network
     *  is up and that other devices can join the network. The state YWireless.WLANSTATE_REJECTED means
     * that the network interface has
     *  not been able to join the requested network. The description of the error can be obtain with the
     * get_message() method.
     *
     *  @return a value among YWireless.WLANSTATE_DOWN, YWireless.WLANSTATE_SCANNING,
     *  YWireless.WLANSTATE_CONNECTED and YWireless.WLANSTATE_REJECTED corresponding to the current state
     * of the wireless interface
     *
     * @throws YAPI_Exception on error
     */
    public int get_wlanState() throws YAPI_Exception
    {
        int res;
        synchronized (this) {
            if (_cacheExpiration <= YAPIContext.GetTickCount()) {
                if (load(_yapi._defaultCacheValidity) != YAPI.SUCCESS) {
                    return WLANSTATE_INVALID;
                }
            }
            res = _wlanState;
        }
        return res;
    }

    /**
     *  Returns the current state of the wireless interface. The state YWireless.WLANSTATE_DOWN means that
     * the network interface is
     *  not connected to a network. The state YWireless.WLANSTATE_SCANNING means that the network interface
     * is scanning available
     *  frequencies. During this stage, the device is not reachable, and the network settings are not yet
     * applied. The state
     *  YWireless.WLANSTATE_CONNECTED means that the network settings have been successfully applied ant
     * that the device is reachable
     *  from the wireless network. If the device is configured to use ad-hoc or Soft AP mode, it means that
     * the wireless network
     *  is up and that other devices can join the network. The state YWireless.WLANSTATE_REJECTED means
     * that the network interface has
     *  not been able to join the requested network. The description of the error can be obtain with the
     * get_message() method.
     *
     *  @return a value among YWireless.WLANSTATE_DOWN, YWireless.WLANSTATE_SCANNING,
     *  YWireless.WLANSTATE_CONNECTED and YWireless.WLANSTATE_REJECTED corresponding to the current state
     * of the wireless interface
     *
     * @throws YAPI_Exception on error
     */
    public int getWlanState() throws YAPI_Exception
    {
        return get_wlanState();
    }

    /**
     * Retrieves a wireless LAN interface for a given identifier.
     * The identifier can be specified using several formats:
     * <ul>
     * <li>FunctionLogicalName</li>
     * <li>ModuleSerialNumber.FunctionIdentifier</li>
     * <li>ModuleSerialNumber.FunctionLogicalName</li>
     * <li>ModuleLogicalName.FunctionIdentifier</li>
     * <li>ModuleLogicalName.FunctionLogicalName</li>
     * </ul>
     *
     * This function does not require that the wireless LAN interface is online at the time
     * it is invoked. The returned object is nevertheless valid.
     * Use the method YWireless.isOnline() to test if the wireless LAN interface is
     * indeed online at a given time. In case of ambiguity when looking for
     * a wireless LAN interface by logical name, no error is notified: the first instance
     * found is returned. The search is performed first by hardware name,
     * then by logical name.
     *
     * If a call to this object's is_online() method returns FALSE although
     * you are certain that the matching device is plugged, make sure that you did
     * call registerHub() at application initialization time.
     *
     * @param func : a string that uniquely characterizes the wireless LAN interface, for instance
     *         YHUBWLN1.wireless.
     *
     * @return a YWireless object allowing you to drive the wireless LAN interface.
     */
    public static YWireless FindWireless(String func)
    {
        YWireless obj;
        YAPIContext ctx = YAPI.GetYCtx(true);
        synchronized (ctx._functionCacheLock) {
            obj = (YWireless) YFunction._FindFromCache("Wireless", func);
            if (obj == null) {
                obj = new YWireless(func);
                YFunction._AddToCache("Wireless", func, obj);
            }
        }
        return obj;
    }

    /**
     * Retrieves a wireless LAN interface for a given identifier in a YAPI context.
     * The identifier can be specified using several formats:
     * <ul>
     * <li>FunctionLogicalName</li>
     * <li>ModuleSerialNumber.FunctionIdentifier</li>
     * <li>ModuleSerialNumber.FunctionLogicalName</li>
     * <li>ModuleLogicalName.FunctionIdentifier</li>
     * <li>ModuleLogicalName.FunctionLogicalName</li>
     * </ul>
     *
     * This function does not require that the wireless LAN interface is online at the time
     * it is invoked. The returned object is nevertheless valid.
     * Use the method YWireless.isOnline() to test if the wireless LAN interface is
     * indeed online at a given time. In case of ambiguity when looking for
     * a wireless LAN interface by logical name, no error is notified: the first instance
     * found is returned. The search is performed first by hardware name,
     * then by logical name.
     *
     * @param yctx : a YAPI context
     * @param func : a string that uniquely characterizes the wireless LAN interface, for instance
     *         YHUBWLN1.wireless.
     *
     * @return a YWireless object allowing you to drive the wireless LAN interface.
     */
    public static YWireless FindWirelessInContext(YAPIContext yctx,String func)
    {
        YWireless obj;
        synchronized (yctx._functionCacheLock) {
            obj = (YWireless) YFunction._FindFromCacheInContext(yctx, "Wireless", func);
            if (obj == null) {
                obj = new YWireless(yctx, func);
                YFunction._AddToCache("Wireless", func, obj);
            }
        }
        return obj;
    }

    /**
     * Registers the callback function that is invoked on every change of advertised value.
     * The callback is invoked only during the execution of ySleep or yHandleEvents.
     * This provides control over the time when the callback is triggered. For good responsiveness, remember to call
     * one of these two functions periodically. To unregister a callback, pass a null pointer as argument.
     *
     * @param callback : the callback function to call, or a null pointer. The callback function should take two
     *         arguments: the function object of which the value has changed, and the character string describing
     *         the new advertised value.
     *
     */
    public int registerValueCallback(UpdateCallback callback)
    {
        String val;
        if (callback != null) {
            YFunction._UpdateValueCallbackList(this, true);
        } else {
            YFunction._UpdateValueCallbackList(this, false);
        }
        _valueCallbackWireless = callback;
        // Immediately invoke value callback with current value
        if (callback != null && isOnline()) {
            val = _advertisedValue;
            if (!(val.equals(""))) {
                _invokeValueCallback(val);
            }
        }
        return 0;
    }

    @Override
    public int _invokeValueCallback(String value)
    {
        if (_valueCallbackWireless != null) {
            _valueCallbackWireless.yNewValue(this, value);
        } else {
            super._invokeValueCallback(value);
        }
        return 0;
    }

    /**
     * Triggers a scan of the wireless frequency and builds the list of available networks.
     * The scan forces a disconnection from the current network. At then end of the process, the
     * the network interface attempts to reconnect to the previous network. During the scan, the wlanState
     * switches to YWireless.WLANSTATE_DOWN, then to YWireless.WLANSTATE_SCANNING. When the scan is completed,
     * get_wlanState() returns either YWireless.WLANSTATE_DOWN or YWireless.WLANSTATE_SCANNING. At this
     * point, the list of detected network can be retrieved with the get_detectedWlans() method.
     *
     * @throws YAPI_Exception on error
     */
    public int startWlanScan() throws YAPI_Exception
    {
        String config;
        config = get_wlanConfig();
        // a full scan is triggered when a config is applied
        return set_wlanConfig(config);
    }

    /**
     * Changes the configuration of the wireless lan interface to connect to an existing
     * access point (infrastructure mode).
     * Remember to call the saveToFlash() method and then to reboot the module to apply this setting.
     *
     * @param ssid : the name of the network to connect to
     * @param securityKey : the network key, as a character string
     *
     * @return YAPI.SUCCESS when the call succeeds.
     *
     * @throws YAPI_Exception on error
     */
    public int joinNetwork(String ssid,String securityKey) throws YAPI_Exception
    {
        return set_wlanConfig(String.format(Locale.US, "INFRA:%s\\%s", ssid,securityKey));
    }

    /**
     * Changes the configuration of the wireless lan interface to create an ad-hoc
     * wireless network, without using an access point. On the YoctoHub-Wireless-g
     * and YoctoHub-Wireless-n,
     * you should use softAPNetwork() instead, which emulates an access point
     * (Soft AP) which is more efficient and more widely supported than ad-hoc networks.
     *
     * When a security key is specified for an ad-hoc network, the network is protected
     * by a WEP40 key (5 characters or 10 hexadecimal digits) or WEP128 key (13 characters
     * or 26 hexadecimal digits). It is recommended to use a well-randomized WEP128 key
     * using 26 hexadecimal digits to maximize security.
     * Remember to call the saveToFlash() method and then to reboot the module
     * to apply this setting.
     *
     * @param ssid : the name of the network to connect to
     * @param securityKey : the network key, as a character string
     *
     * @return YAPI.SUCCESS when the call succeeds.
     *
     * @throws YAPI_Exception on error
     */
    public int adhocNetwork(String ssid,String securityKey) throws YAPI_Exception
    {
        return set_wlanConfig(String.format(Locale.US, "ADHOC:%s\\%s", ssid,securityKey));
    }

    /**
     * Changes the configuration of the wireless lan interface to create a new wireless
     * network by emulating a WiFi access point (Soft AP). This function can only be
     * used with the YoctoHub-Wireless-g and the YoctoHub-Wireless-n.
     *
     * On the YoctoHub-Wireless-g, when a security key is specified for a SoftAP network,
     * the network is protected by a WEP40 key (5 characters or 10 hexadecimal digits) or
     * WEP128 key (13 characters or 26 hexadecimal digits). It is recommended to use a
     * well-randomized WEP128 key using 26 hexadecimal digits to maximize security.
     *
     * On the YoctoHub-Wireless-n, when a security key is specified for a SoftAP network,
     * the network will be protected by WPA2.
     *
     * Remember to call the saveToFlash() method and then to reboot the module to apply this setting.
     *
     * @param ssid : the name of the network to connect to
     * @param securityKey : the network key, as a character string
     *
     * @return YAPI.SUCCESS when the call succeeds.
     *
     * @throws YAPI_Exception on error
     */
    public int softAPNetwork(String ssid,String securityKey) throws YAPI_Exception
    {
        return set_wlanConfig(String.format(Locale.US, "SOFTAP:%s\\%s", ssid,securityKey));
    }

    /**
     * Returns a list of YWlanRecord objects that describe detected Wireless networks.
     * This list is not updated when the module is already connected to an access point (infrastructure mode).
     * To force an update of this list, startWlanScan() must be called.
     * Note that an languages without garbage collections, the returned list must be freed by the caller.
     *
     * @return a list of YWlanRecord objects, containing the SSID, channel,
     *         link quality and the type of security of the wireless network.
     *
     * @throws YAPI_Exception on error
     */
    public ArrayList<YWlanRecord> get_detectedWlans() throws YAPI_Exception
    {
        byte[] json = new byte[0];
        ArrayList<String> wlanlist = new ArrayList<>();
        ArrayList<YWlanRecord> res = new ArrayList<>();

        json = _download("wlan.json?by=name");
        wlanlist = _json_get_array(json);
        res.clear();
        for (String ii:wlanlist) {
            res.add(new YWlanRecord(ii));
        }
        return res;
    }

    /**
     * Continues the enumeration of wireless LAN interfaces started using yFirstWireless().
     * Caution: You can't make any assumption about the returned wireless LAN interfaces order.
     * If you want to find a specific a wireless LAN interface, use Wireless.findWireless()
     * and a hardwareID or a logical name.
     *
     * @return a pointer to a YWireless object, corresponding to
     *         a wireless LAN interface currently online, or a null pointer
     *         if there are no more wireless LAN interfaces to enumerate.
     */
    public YWireless nextWireless()
    {
        String next_hwid;
        try {
            String hwid = _yapi._yHash.resolveHwID(_className, _func);
            next_hwid = _yapi._yHash.getNextHardwareId(_className, hwid);
        } catch (YAPI_Exception ignored) {
            next_hwid = null;
        }
        if(next_hwid == null) return null;
        return FindWirelessInContext(_yapi, next_hwid);
    }

    /**
     * Starts the enumeration of wireless LAN interfaces currently accessible.
     * Use the method YWireless.nextWireless() to iterate on
     * next wireless LAN interfaces.
     *
     * @return a pointer to a YWireless object, corresponding to
     *         the first wireless LAN interface currently online, or a null pointer
     *         if there are none.
     */
    public static YWireless FirstWireless()
    {
        YAPIContext yctx = YAPI.GetYCtx(false);
        if (yctx == null)  return null;
        String next_hwid = yctx._yHash.getFirstHardwareId("Wireless");
        if (next_hwid == null)  return null;
        return FindWirelessInContext(yctx, next_hwid);
    }

    /**
     * Starts the enumeration of wireless LAN interfaces currently accessible.
     * Use the method YWireless.nextWireless() to iterate on
     * next wireless LAN interfaces.
     *
     * @param yctx : a YAPI context.
     *
     * @return a pointer to a YWireless object, corresponding to
     *         the first wireless LAN interface currently online, or a null pointer
     *         if there are none.
     */
    public static YWireless FirstWirelessInContext(YAPIContext yctx)
    {
        String next_hwid = yctx._yHash.getFirstHardwareId("Wireless");
        if (next_hwid == null)  return null;
        return FindWirelessInContext(yctx, next_hwid);
    }

    //--- (end of generated code: YWireless implementation)
}

