package io.github.hsyyid.adminshop.utils;

import io.github.hsyyid.adminshop.AdminShop;

import java.io.IOException;
import java.util.UUID;

import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class LocationAdapter extends TypeAdapter<Location<World>>
{
	@Override
	public void write(JsonWriter out, Location<World> location) throws IOException
	{
		if (location == null)
		{
			out.nullValue();
			return;
		}

		out.beginObject();

		out.name("world");
		out.value(location.getExtent().getUniqueId().toString());

		out.name("x");
		out.value(location.getX());

		out.name("y");
		out.value(location.getY());

		out.name("z");
		out.value(location.getZ());

		out.endObject();
	}

	@Override
	public Location<World> read(JsonReader in) throws IOException
	{
		if (in.peek() == JsonToken.NULL)
		{
			return null;
		}

		in.beginObject();

		in.nextName();
		String worldID = in.nextString();

		in.nextName();
		double x = in.nextDouble();

		in.nextName();
		double y = in.nextDouble();

		in.nextName();
		double z = in.nextDouble();

		in.endObject();

		if (AdminShop.game.getServer().getWorld(UUID.fromString(worldID)).isPresent())
		{
			Location<World> location = new Location<World>(AdminShop.game.getServer().getWorld(UUID.fromString(worldID)).get(), x, y, z);
			return location;
		}
		else
		{
			System.out.println("Error! Location's world for AdminShop not found. World UUID: " + worldID);
			return null;
		}
	}

}
