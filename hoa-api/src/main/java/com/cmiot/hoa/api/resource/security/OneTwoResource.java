package com.cmiot.hoa.api.resource.security;

import com.cmiot.hoa.api.resource.BaseResource;
import io.netty.channel.ChannelHandlerContext;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ZJL on 2016/9/7.
 */
@Path("/")
public class OneTwoResource extends BaseResource {

    /**
     * 该方法走IP白名单认证
     *
     * @param ctx
     * @param jsonText
     * @return
     */
    @POST
    @Path("{path}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response indexJson(@Context ChannelHandlerContext ctx, @PathParam("path") String path, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getHgSystemInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHgSystemInfo(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getHgResourceUsage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHgResourceUsage(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getHgTimeDuration")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHgTimeDuration(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getHgInfoAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHgInfoAll(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getWanPppoeStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWanPppoeStatus(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getWanInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWanInfo(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setWifiSsidInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setWifiSsidInfo(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getWifiSsidInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWifiSsidInfo(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setWifiWpsOn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setWifiWpsOn(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getWifiWpsStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWifiWpsStatus(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setWifiWpsOff")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setWifiWpsOff(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setWifiSsidOnoff")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setWifiSsidOnoff(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setWifiOnoffTimer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setWifiOnoffTimer(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getWifiOnoffTimer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWifiOnoffTimer(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setHgName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setHgName(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setLanDeviceName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setLanDeviceName(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getHgNamelist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHgNamelist(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getLanNetInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanNetInfo(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setLanAccess")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setLanAccess(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getLanAccessNet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanAccessNet(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getLanAccessStorage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanAccessStorage(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setLanDeviceOnline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setLanDeviceOnline(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportLanDeviceOnline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportLanDeviceOnline(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getLanDeviceOnline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanDeviceOnline(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getHgPortsTrafficStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHgPortsTrafficStatus(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setLanDeviceBandwidth")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setLanDeviceBandwidth(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getLanDeviceBandth")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanDeviceBandth(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setLanDeviceSpeedTest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setLanDeviceSpeedTest(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getLanDeviceTrafficStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanDeviceTrafficStatus(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setLanSpeedReportPolicy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setLanSpeedReportPolicy(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportLanDevicespeed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportLanDevicespeed(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getLanSpeedReportPolicy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanSpeedReportPolicy(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setHgReboot")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setHgReboot(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setHgServiceManage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setHgServiceManage(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setHgServiceAccount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setHgServiceAccount(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getHgServiceInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHgServiceInfo(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("setHgAdminPwd")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setHgAdminPwd(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportUpgradePlan")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportUpgradePlan(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportDeleteUpgradePlan")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportDeleteUpgradePlan(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyQueryUpgradeStat")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyQueryUpgradeStat(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportHgBasicInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportHgBasicInfo(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportBootInitiation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportBootInitiation(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportHGOnline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportHGOnline(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportUnbindHG")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportUnbindHG(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyBindHG")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyBindHG(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getBindHG")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBindHG(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyInstallPlugin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyInstallPlugin(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportInstallPluginResult")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportInstallPluginResult(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyQueryInstallProcess")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyQueryInstallProcess(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportInstallProgress")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportInstallProgress(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyInstallCancel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyInstallCancel(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportInstallCancelResult")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportInstallCancelResult(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyUninstallPlugin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyUninstallPlugin(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportUninstallResult")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportUninstallResult(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyEnablePlugin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyEnablePlugin(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportEnablePlugin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportEnablePlugin(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyResetPlugin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyResetPlugin(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyListPlugin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyListPlugin(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportPluginUp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportPluginUp(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyPluginUpResult")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyPluginUpResult(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportPluginDown")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportPluginDown(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyPluginDownResult")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyPluginDownResult(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportPluginUpgrade")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportPluginUpgrade(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportPluginUpgradeResult")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportPluginUpgradeResult(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("getPluginAuditState")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPluginAuditState(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyPluginOnline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyPluginOnline(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyPluginOffline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyPluginOffline(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("reportTokenValidate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportTokenValidate(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

    @POST
    @Path("notifyOrderBusiness")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifyOrderBusiness(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }


    @POST
    @Path("notifySyncBusinessOrder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response notifySyncBusinessOrder(@Context ChannelHandlerContext ctx, String jsonText) {
        return SecurityResource.privateJson(ctx, jsonText);
    }

}
