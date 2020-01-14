package com.example.entity;

import java.io.Serializable;

/**
 * security_matcher
 * @author 
 */
public class SecurityMatcher implements Serializable {
    private Integer id;

    private String matchers;

    private String rolesName;

    private String authoritysName;

    private String ipAddress;

    private Integer level;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatchers() {
        return matchers;
    }

    public void setMatchers(String matchers) {
        this.matchers = matchers;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    public String getAuthoritysName() {
        return authoritysName;
    }

    public void setAuthoritysName(String authoritysName) {
        this.authoritysName = authoritysName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SecurityMatcher other = (SecurityMatcher) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMatchers() == null ? other.getMatchers() == null : this.getMatchers().equals(other.getMatchers()))
            && (this.getRolesName() == null ? other.getRolesName() == null : this.getRolesName().equals(other.getRolesName()))
            && (this.getAuthoritysName() == null ? other.getAuthoritysName() == null : this.getAuthoritysName().equals(other.getAuthoritysName()))
            && (this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMatchers() == null) ? 0 : getMatchers().hashCode());
        result = prime * result + ((getRolesName() == null) ? 0 : getRolesName().hashCode());
        result = prime * result + ((getAuthoritysName() == null) ? 0 : getAuthoritysName().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", matchers=").append(matchers);
        sb.append(", rolesName=").append(rolesName);
        sb.append(", authoritysName=").append(authoritysName);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", level=").append(level);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}