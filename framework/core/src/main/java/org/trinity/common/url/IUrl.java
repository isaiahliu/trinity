package org.trinity.common.url;

public interface IUrl
{
	default String getSplitter()
	{
		return "/";
	}

	IUrl getParent();

	String getPath();

	default String getFullPath()
	{
		String path = getPath();

		final IUrl parent = getParent();

		if (parent != null)
		{
			path = getParent().getFullPath() + getSplitter() + path;
		}

		return path;
	}
}
