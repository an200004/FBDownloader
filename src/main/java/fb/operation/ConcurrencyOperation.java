package fb.operation;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fb.album.FBPageAlbumOperation;

public class ConcurrencyOperation {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);
		
		Runnable allPagesTask = () -> {
			PageOperation po = new PageOperation();
			po.allPages();
			po = null;
		};
		
		scheduledExecutor.scheduleWithFixedDelay(allPagesTask, 0, 2, TimeUnit.MINUTES);
		
		Runnable getPageAlbumsTask = () -> {
			PageOperation po = new PageOperation();
			ExecutorService executor = Executors.newFixedThreadPool(10);
			po.findRequiredPages().forEach(page -> {
				executor.submit(() -> {
					PageOperation poGetAlbums = new PageOperation();
					poGetAlbums.getPageAlbums(page);
				});
			});
		};
		
		scheduledExecutor.scheduleWithFixedDelay(getPageAlbumsTask, 0, 2, TimeUnit.MINUTES);
		
		for (int i = 0; i < 2; i++) {
			scheduledExecutor.scheduleWithFixedDelay(() -> {
				FBPageAlbumOperation fbPageOperation = new FBPageAlbumOperation();
				fbPageOperation.getFBPageAlbum();
			}, 0, 1, TimeUnit.SECONDS);
		}
		
		for (int i = 0; i < 2; i++) {
			scheduledExecutor.scheduleWithFixedDelay(() -> {
				PhotoOperation po = new PhotoOperation();
				po.retrievePhotoDetail();
			}, 0, 1, TimeUnit.SECONDS);
		}
		
		for (int i = 0; i < 2; i++) {
			scheduledExecutor.scheduleWithFixedDelay(() -> {
				PhotoOperation po = new PhotoOperation();
				po.downloadPhoto();
			}, 0, 1, TimeUnit.SECONDS);
		}
						
	}
	
}
