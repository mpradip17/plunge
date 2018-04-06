
package com.plunge.myplunge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("order_quantity")
    @Expose
    private Integer orderQuantity;
    @SerializedName("order_price")
    @Expose
    private Integer orderPrice;
    @SerializedName("oder_for_price")
    @Expose
    private Integer oderForPrice;
    @SerializedName("order_total_price")
    @Expose
    private Integer orderTotalPrice;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("order_for_table_id")
    @Expose
    private String orderForTableId;
    @SerializedName("approved_by")
    @Expose
    private Integer approvedBy;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("updated_by")
    @Expose
    private Integer updatedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("mandi_id")
    @Expose
    private Integer mandiId;
    @SerializedName("reference_id")
    @Expose
    private String referenceId;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("inventory_location_id")
    @Expose
    private Integer inventoryLocationId;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("inventory_type")
    @Expose
    private String inventoryType;
    @SerializedName("mandis_name")
    @Expose
    private String mandisName;
    @SerializedName("inventory_manager")
    @Expose
    private String inventoryManager;
    @SerializedName("inventory_address")
    @Expose
    private String inventoryAddress;
    @SerializedName("arthiya_name")
    @Expose
    private String arthiyaName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOderForPrice() {
        return oderForPrice;
    }

    public void setOderForPrice(Integer oderForPrice) {
        this.oderForPrice = oderForPrice;
    }

    public Integer getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(Integer orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderForTableId() {
        return orderForTableId;
    }

    public void setOrderForTableId(String orderForTableId) {
        this.orderForTableId = orderForTableId;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getMandiId() {
        return mandiId;
    }

    public void setMandiId(Integer mandiId) {
        this.mandiId = mandiId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(Integer inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getMandisName() {
        return mandisName;
    }

    public void setMandisName(String mandisName) {
        this.mandisName = mandisName;
    }

    public String getInventoryManager() {
        return inventoryManager;
    }

    public void setInventoryManager(String inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public String getInventoryAddress() {
        return inventoryAddress;
    }

    public void setInventoryAddress(String inventoryAddress) {
        this.inventoryAddress = inventoryAddress;
    }

    public String getArthiyaName() {
        return arthiyaName;
    }

    public void setArthiyaName(String arthiyaName) {
        this.arthiyaName = arthiyaName;
    }

}
