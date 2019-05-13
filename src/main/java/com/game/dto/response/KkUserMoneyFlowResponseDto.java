package com.game.dto.response;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.time.LocalDateTime;
    import java.io.Serializable;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author yeats
* @since 2019-05-04
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)

    @ApiModel(value="KkUserMoneyFlowResponseDto对象", description="")
    public class KkUserMoneyFlowResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String pfmUserId;

            @ApiModelProperty(value = "平台标识PT,AG,IM..")
    private String gamesFlag;

            @ApiModelProperty(value = "交易大分类参考表：kk_user_flow_type_detail，01入款 02出款 04彩票 05活动")
    private String changeType;

            @ApiModelProperty(value = "参考kk_user_flow_type_detail 05充值 06提款 01下注 02中奖 03反水 04撤单
10跟单赚佣金,11跟单付佣金,20活动彩金")
    private String changeTypeStatus;

            @ApiModelProperty(value = "以千为单位")
    private Long changeMoney;

            @ApiModelProperty(value = "变动后余额，以千为单位")
    private Long changeBalance;

    private String optUser;

    private String remark;

            @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;


}
