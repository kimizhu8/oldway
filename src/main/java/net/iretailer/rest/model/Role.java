package net.iretailer.rest.model;

public class Role {
    private Integer roleId;

    private Integer operationType;

    private String menu;

    private String roleName;

    private String homeSettings;

    private String iconAddress;

    private String logoAddress;

    private String backgroundAddress;

    private String[] roleSiteArray;
    
    private String userName;
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getRoleSiteArray() {
		return roleSiteArray;
	}

	public void setRoleSiteArray(String[] roleSiteArray) {
		this.roleSiteArray = roleSiteArray;
	}

	public String getRoleSite() {
		return roleSite;
	}

	public void setRoleSite(String roleSite) {
		this.roleSite = roleSite;
	}

	private String bottomText;
    
    private String roleSite;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu == null ? null : menu.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getHomeSettings() {
        return homeSettings;
    }

    public void setHomeSettings(String homeSettings) {
        this.homeSettings = homeSettings == null ? null : homeSettings.trim();
    }

    public String getIconAddress() {
        return iconAddress;
    }

    public void setIconAddress(String iconAddress) {
        this.iconAddress = iconAddress == null ? null : iconAddress.trim();
    }

    public String getLogoAddress() {
        return logoAddress;
    }

    public void setLogoAddress(String logoAddress) {
        this.logoAddress = logoAddress == null ? null : logoAddress.trim();
    }

    public String getBackgroundAddress() {
        return backgroundAddress;
    }

    public void setBackgroundAddress(String backgroundAddress) {
        this.backgroundAddress = backgroundAddress == null ? null : backgroundAddress.trim();
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText == null ? null : bottomText.trim();
    }
}