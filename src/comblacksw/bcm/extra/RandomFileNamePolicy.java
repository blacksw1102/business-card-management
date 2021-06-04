package comblacksw.bcm.extra;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class RandomFileNamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File file) {
		String name = file.getName();
		String extension  = name.substring(name.lastIndexOf("."), name.length());
		UUID uuid = UUID.randomUUID();
		String newFileName = uuid.toString() + extension;
		
		System.out.println("file name : " + name);
		System.out.println("extension : " + extension);
		System.out.println("newFileName : " + newFileName);
		
		File renameFile = new File(file.getParent(), newFileName);
		return renameFile;
	}

}
