/**
 * $Id: EOrgUserCount.java,v 1.0 2021/10/31 14:25 zengws Exp $
 * <p>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.mou.election.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zengws
 * @version $Id: EOrgUserCount.java,v 1.1 2021/10/31 14:25 zengws Exp $
 * Created on 2021/10/31 14:25
 */
@Getter
@Setter
@ToString
public class EOrgUserCountRequest {

    private Long organizationId;

    private int currentPageNo;

    private int pageSize;

}