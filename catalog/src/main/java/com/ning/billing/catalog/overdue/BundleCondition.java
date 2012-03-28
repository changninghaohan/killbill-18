/*
 * Copyright 2010-2011 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.catalog.overdue;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;

import org.joda.time.DateTime;

import com.ning.billing.catalog.DefaultPriceList;
import com.ning.billing.catalog.DefaultProduct;
import com.ning.billing.catalog.api.BillingPeriod;
import com.ning.billing.catalog.api.PhaseType;
import com.ning.billing.catalog.api.overdue.BillingState;
import com.ning.billing.catalog.api.overdue.BillingStateBundle;
import com.ning.billing.entitlement.api.user.SubscriptionBundle;

@XmlAccessorType(XmlAccessType.NONE)
public class BundleCondition extends DefaultCondition<SubscriptionBundle> {
	@XmlElement(required=false, name="basePlanProduct")
    @XmlIDREF
    private DefaultProduct basePlanProduct;
	    
    @XmlElement(required=false, name="basePlanBillingPeriod")
    private BillingPeriod basePlanBillingPeriod;
    
    @XmlElement(required=false, name="basePlanPriceList")
    @XmlIDREF
    private DefaultPriceList basePlanPriceList;
    
    @XmlElement(required=false)
    private PhaseType basePlanPhaseType;    


	public boolean evaluate(BillingStateBundle state, DateTime now) {
		return super.evaluate((BillingState)state, now) &&
				(basePlanProduct       == null || basePlanProduct.equals(state.getBasePlanProduct())) &&
				(basePlanBillingPeriod == null || basePlanBillingPeriod.equals(state.getBasePlanBillingPeriod())) &&
                (basePlanPriceList     == null || basePlanPriceList.equals(state.getBasePlanPriceList())) &&              
                (basePlanPhaseType     == null || basePlanPhaseType.equals(state.getBasePlanPhaseType()));              
	}

	protected BundleCondition setBasePlanProduct(DefaultProduct basePlanProduct) {
		this.basePlanProduct = basePlanProduct;
		return this;
	}

	protected BundleCondition setBasePlanBillingPeriod(BillingPeriod basePlanBillingPeriod) {
		this.basePlanBillingPeriod = basePlanBillingPeriod;
		return this;
	}

	protected BundleCondition setBasePlanPriceList(DefaultPriceList basePlanPriceList) {
		this.basePlanPriceList = basePlanPriceList;
		return this;
	}
	
	
}
