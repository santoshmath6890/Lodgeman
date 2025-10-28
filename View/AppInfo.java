/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.view;

import javafx.stage.Stage;

/**
 *
 * @author svastrad
 */
public class AppInfo {
    
    private AppInfo() {
        
    }

    public AppInfo(Stage mPrimaryStage, LodgeMan mApplication) {
        this.mPrimaryStage = mPrimaryStage;
        this.mApplication = mApplication;
    }

    public Stage getmPrimaryStage() {
        return mPrimaryStage;
    }

    public void setmPrimaryStage(Stage mPrimaryStage) {
        this.mPrimaryStage = mPrimaryStage;
    }

    public LodgeMan getmApplication() {
        return mApplication;
    }

    public void setmApplication(LodgeMan mApplication) {
        this.mApplication = mApplication;
    }
    
    public static AppInfo getInstance() {
        return AppInfoHolder.INSTANCE;
    }
    
    private static class AppInfoHolder {

        private static final AppInfo INSTANCE = new AppInfo();
    }
    Stage mPrimaryStage;
    LodgeMan mApplication;
    
}
