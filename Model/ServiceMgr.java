/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.model.business.Service;

/**
 *
 * @author vijay
 */
public class ServiceMgr {
    
    private ServiceMgr() {
    }
    
    public static ServiceMgr getInstance() {
        return ServiceMgrHolder.INSTANCE;
    }
    
    private static class ServiceMgrHolder {

        private static final ServiceMgr INSTANCE = new ServiceMgr();
    }
}
