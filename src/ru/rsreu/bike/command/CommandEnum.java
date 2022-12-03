package ru.rsreu.bike.command;

import ru.rsreu.bike.command.admin.ChangeClientCommand;
import ru.rsreu.bike.command.admin.CreateClientCommand;
import ru.rsreu.bike.command.admin.DeleteClientCommand;
import ru.rsreu.bike.command.admin.GetAdminPageCommand;
import ru.rsreu.bike.command.admin.OpenPageChangeClientCommand;
import ru.rsreu.bike.command.admin.OpenPageCreateClientCommand;
import ru.rsreu.bike.command.client.BookBikeCommand;
import ru.rsreu.bike.command.client.DriveBikeCommand;
import ru.rsreu.bike.command.client.GetUserBikesCommand;
import ru.rsreu.bike.command.client.GetUserPageCommand;
import ru.rsreu.bike.command.client.LeaseBikeCommand;
import ru.rsreu.bike.command.client.SelectBikeCommand;
import ru.rsreu.bike.command.client.UnleaseBikeCommand;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.moderator.BlockClientCommand;
import ru.rsreu.bike.moderator.CreateAddressCommand;
import ru.rsreu.bike.moderator.CreateBikeCommand;
import ru.rsreu.bike.moderator.DeleteAddressCommand;
import ru.rsreu.bike.moderator.GetAddressCommand;
import ru.rsreu.bike.moderator.GetAllBikeCommand;
import ru.rsreu.bike.moderator.GetModeratorPageCommand;
import ru.rsreu.bike.moderator.GetTripCommand;
import ru.rsreu.bike.moderator.OpenPageCreateAddressCommand;
import ru.rsreu.bike.moderator.OpenPageCreateBikeCommand;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	DRIVE {
		{
			this.command = new DriveBikeCommand();
		}
	},
	CREATE_ADDRESS {
		{
			this.command = new CreateAddressCommand();
		}
	},ALL_BIKE{
		{
			this.command = new GetAllBikeCommand();
		}
	},
	OPEN_PAGE_CREATE_ADDRESS {
		{
			this.command = new OpenPageCreateAddressCommand();
		}
	},
	CREATE_BIKE {
		{
			this.command = new CreateBikeCommand();
		}
	},
	OPEN_PAGE_CREATE_BIKE {
		{
			this.command = new OpenPageCreateBikeCommand();
		}
	},
	GET_ADDRESS {
		{
			this.command = new GetAddressCommand();
		}
	},
	GET_TRIP {
		{
			this.command = new GetTripCommand();
		}
	},
	DELETE_ADDRESS {
		{
			this.command = new DeleteAddressCommand();
		}
	},
	GET_BIKES {
		{
			this.command = new GetUserBikesCommand();

		}
	},
	USER_PAGE {
		{
			this.command = new GetUserPageCommand();
		}
	},
	SELECT_BIKE {
		{
			this.command = new SelectBikeCommand();
		}
	},
	BOOK {
		{
			this.command = new BookBikeCommand();
		}
	},
	UNLEASE {
		{
			this.command = new UnleaseBikeCommand();
		}
	},
	LEASE {
		{
			this.command = new LeaseBikeCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	ADMIN_PAGE {
		{
			this.command = new GetAdminPageCommand();
		}
	},
	MODERATOR_PAGE {
		{
			this.command = new GetModeratorPageCommand();
		}
	},
	CHANGE_CLIENT {
		{
			this.command = new ChangeClientCommand();
		}
	},
	OPEN_PAGE_CHANGE_CLIENT {
		{
			this.command = new OpenPageChangeClientCommand();
		}
	},
	CREATE_CLIENT {
		{
			this.command = new CreateClientCommand();
		}
	},
	OPEN_PAGE_CREATE_CLIENT {
		{
			this.command = new OpenPageCreateClientCommand();
		}
	},
	DELETE_CLIENT {
		{
			this.command = new DeleteClientCommand();
		}
	},
	BLOCK {
		{
			this.command = new BlockClientCommand();
		}
	};

	public ActivityCommand getCommand() {
		return command;
	}

	ActivityCommand command;
}
