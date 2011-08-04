/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.services.seo;

import javax.jcr.Node;

import org.exoplatform.services.wcm.BaseWCMTestCase;
import org.exoplatform.services.seo.SEOService;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jun 21, 2011  
 */
public class TestSEOService extends BaseWCMTestCase{
  
  /** The SEO Service. */
  private SEOService seoService;
 
  /* (non-Javadoc)
   * @see org.exoplatform.services.seo.TestSEOService#setUp()
   */
  public void setUp() throws Exception {
    super.setUp();
    seoService =  getService(SEOService.class);    
  }
  
  public void testConstruct() throws Exception{
    assertNotNull(seoService);
  }
  
  /**
   * test store
   * @throws Exception
   */
  public void testStorePageMetadata() throws Exception {
   PageMetadataModel metaModel = new PageMetadataModel();
    metaModel.setUri("home");
    metaModel.setPageReference("home");
    metaModel.setKeywords("test");
    metaModel.setRobotsContent("index,follow");
    metaModel.setSiteMap(true);
    metaModel.setDescription("test description");
    metaModel.setPriority(0);
    seoService.storePageMetadata(metaModel,"classic",false);
    PageMetadataModel retrieveModel = seoService.getPageMetadata("home");
    assertEquals(retrieveModel.getKeywords(), "test");
  }
  
  /**
   * test remove metedate
   * @return void
   */
  public void tesRemoveMetadata() throws Exception{
    PageMetadataModel metaModel = new PageMetadataModel();    
    metaModel.setUri("home");
    metaModel.setKeywords("test");    
    metaModel.setRobotsContent("index,follow");    
    seoService.storePageMetadata(metaModel,"classic",false);
    assertEquals("test", seoService.getPageMetadata("home").getKeywords());
    seoService.removePageMetadata(metaModel, "classic",false);
    assertNull(seoService.getPageMetadata("home"));     
  }
  
  /**
   * Gets the service.
   *
   * @param clazz the clazz
   *
   * @return the service
   */
  protected <T> T getService(Class<T> clazz) {
    return clazz.cast(container.getComponentInstanceOfType(clazz));
  }
}
