/**
 * $Id: EOrgUserCountDTO.java,v 1.0 2021/10/31 14:40 zengws Exp $
 * <p>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.mou.election.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zengws
 * @version $Id: EOrgUserCountDTO.java,v 1.1 2021/10/31 14:40 zengws Exp $
 * Created on 2021/10/31 14:40
 */
@Getter
@Setter
@ToString
public class EOrgUserCountDTO {

    private Long organizationId;

    private int currentPageNo;

    private int pageSize;
}