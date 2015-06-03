package org.sharks.service;

import lombok.Getter;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CacheService {

	public ClearCacheStatus clearCaches(String passphrase);
	public ClearCacheStatus getClearCacheStatus();
	
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
	
	public class WrongPasswordException extends RuntimeException {
		private static final long serialVersionUID = -9098458177389003253L;
	}

}