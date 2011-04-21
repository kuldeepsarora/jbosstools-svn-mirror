/******************************************************************************* 
 * Copyright (c) 2009 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.jst.web.kb;

import org.jboss.tools.common.text.TextProposal;

/**
 * @author Alexey Kazakov
 */
public interface IProposalProcessor {
	public TextProposal[] EMPTY_PROPOSAL_LIST = new TextProposal[0];

	/**
	 * @return proposals
	 */
	public TextProposal[] getProposals(KbQuery query, IPageContext context);

}