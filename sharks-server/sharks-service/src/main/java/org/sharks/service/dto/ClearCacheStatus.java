/**
 * 
 */
package org.sharks.service.dto;

import lombok.Getter;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Getter
public class ClearCacheStatus {
	
	public static ClearCacheStatus IDLE = new ClearCacheStatus(OperationStatus.IDLE, null);
	public static ClearCacheStatus ONGOING = new ClearCacheStatus(OperationStatus.ONGOING, null);
	public static ClearCacheStatus FAILED(String reason) {
		return new ClearCacheStatus(OperationStatus.FAILED, reason);
	}
	
	private OperationStatus status;
	private String reason;
	
	private ClearCacheStatus(OperationStatus status, String reason) {
		this.status = status;
		this.reason = reason;
	}
	
	public enum OperationStatus {
		IDLE,
		ONGOING,
		FAILED;
	}
}