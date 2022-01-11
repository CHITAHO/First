library ieee ;
use ieee.std_logic_1164.all ;

entity iobuf is
	port (
		enable : in std_logic;
		d_out : out std_logic;
		data : inout std_logic
	);
end iobuf;

architecture behavioral of iobuf is
begin
	
end ;

library ieee;
use ieee.std_logic_1164.all;

entity tb_2iobuf is
end tb_2iobuf;

architecture sim of tb_2iobuf is
component iobuf 
	port (
		enable : in std_logic;
		d_out : out std_logic;
		data : inout std_logic
	);
end component;

	signal d_in1, enable1 : std_logic;
	signal d_in2, enable2 : std_logic;
	signal data : std_logic;

begin
	
	enable1 <= '0', '1' after 20 ns, '0' after 50 ns, '1' after 60 ns, '0' after 90 ns;	
	
	
	enable2 <= '0', '1' after 40 ns, '0' after 60 ns, '1' after 80 ns, '0' after 100 ns;	
	
	u0 : iobuf port map(enable=>enable1, d_out=>open, data=>data);
	u2 : iobuf port map(enable=>enable2, d_out=>open, data=>data);
	
end sim ;
